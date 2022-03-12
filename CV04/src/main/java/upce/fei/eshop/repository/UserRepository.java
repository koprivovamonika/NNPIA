package upce.fei.eshop.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import upce.fei.eshop.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = "orders")
    Optional<User> findByEmailContains(String contains);
}
