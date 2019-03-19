package oslomet.no.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oslomet.no.demo.model.Ticket;
import oslomet.no.demo.repository.TicketRepsitory;

import java.util.List;

@Service
public class TicketService {



    @Autowired
    public TicketRepsitory ticketRepsitory;

    public List<Ticket> getAllTickets(){
        return ticketRepsitory.findAll();
    }

    public Ticket getTicketById(long id){
        return ticketRepsitory.findById(id).get();
    }


    public void deleteTicketById(long id){
        ticketRepsitory.deleteById(id);
    }

    public Ticket saveTicket(Ticket ticket){
        return ticketRepsitory.save(ticket);
    }

    public Ticket updateTicketById(Ticket ticket){
        return ticketRepsitory.save(ticket);
    }

}
