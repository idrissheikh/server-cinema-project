package oslomet.no.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import oslomet.no.demo.model.Ticket;

import java.util.Optional;

public interface TicketRepsitory extends JpaRepository<Ticket, Long> {
     Optional<Ticket> findById(int Id);

}

