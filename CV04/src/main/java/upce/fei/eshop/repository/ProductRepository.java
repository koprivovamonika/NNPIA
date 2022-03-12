package upce.fei.eshop.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upce.fei.eshop.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"orderHasProducts"})
    Product findProductByNameContains(String contains);

    @EntityGraph(attributePaths = {"orderHasProducts"})
    @Query("select p from Product p where p.id between 1 and 2")
    List<Product> findProductByIdBetween(Long start, Long finish);
}
