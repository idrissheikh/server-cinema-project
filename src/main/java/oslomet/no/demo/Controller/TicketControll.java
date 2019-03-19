package oslomet.no.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import oslomet.no.demo.model.Ticket;
import oslomet.no.demo.model.User;
import oslomet.no.demo.repository.TicketRepsitory;
import oslomet.no.demo.repository.UserRepository;
import oslomet.no.demo.service.TicketService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TicketControll {



    @Autowired
    private TicketRepsitory ticketRepsitory;

    @Autowired
    TicketService ticketService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(){
        return  "This is a rest controller";
    }



    @GetMapping("/home")
    public String homePage(Model model ){

        List<Ticket> ticketList = ticketRepsitory.findAll();

        model.addAttribute("ticketes", ticketList);
        System.out.println("ticket inholder: " + ticketList.size());


        return "index";

    }
    @GetMapping("/shipping/{id}")
    public String bestille(@PathVariable("id") String id, Model model, User user) {

        List<Ticket> ticketList1 = new ArrayList<>();
        Ticket ticket = this.ticketRepsitory.findById(Long.parseLong(id)).get();

        ticketList1.add(ticket);
        model.addAttribute("ticket", ticket);
        return "order";
    }


    @GetMapping("/tickets")
    public List<Ticket> GetAllBulding(){
        return ticketService.getAllTickets();
    }


    @GetMapping("/tickets/{id}")
    public  Ticket getTicketById(@PathVariable long id){
        return  ticketService.getTicketById(id);

    }

    @DeleteMapping("/tickets/{id}")
    public  void deleteTicketById(@PathVariable long id){
        ticketService.deleteTicketById(id);

    }

    @PostMapping("/tickets")
    public  Ticket saveTicket(@RequestBody Ticket ticket){
        return  ticketService.saveTicket(ticket);

    }

    @PutMapping("tickets/{id}")
    public  Ticket updateTicketById(@PathVariable long id ,@RequestBody Ticket ticket){
        return  ticketService.updateTicketById(ticket);

    }

   // @PostMapping("/tickets")
    public static String getDateTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        return strDate;
    }

    @PostMapping ("/tickets/order/{id}")
    public Ticket orderTicket(@PathVariable long id, @RequestBody  User user ){
        Ticket ticket = ticketService.getTicketById(id);
        Optional<User> userdb = userRepository.findUserByEmail(user.getEmail());
        if(!userdb.isPresent()){
            userRepository.save(user);
        }

        user = userRepository.findUserByEmail(user.getEmail()).get();
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);
        user.setTicketList(ticketList);
        ticket.setUser(user);
        userRepository.save(user);
        ticketRepsitory.save(ticket);

        return ticket;

    }

    @GetMapping("/tickets/email={email}")
    public List<Ticket> getTicketsByEmail(@PathVariable("email") String email){
        List<Ticket> result = new ArrayList<>();
        for(Ticket ticket : ticketService.getAllTickets()){
            if(ticket.getUser() != null ){
                if(ticket.getUser().getEmail().equals(email)){
                    result.add(ticket);
                }
            }

        }

        return result;
    }
}