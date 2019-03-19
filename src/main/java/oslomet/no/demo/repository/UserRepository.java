package oslomet.no.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import oslomet.no.demo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
