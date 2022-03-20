package upce.fei.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upce.fei.eshop.entity.Producer;

import java.util.Optional;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Optional<Producer> findProducerByNameContains(String nameContains);
}
