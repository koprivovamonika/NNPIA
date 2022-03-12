package upce.fei.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upce.fei.eshop.entity.OrderHasProduct;

import java.util.List;

public interface OrderHasProductRepository extends JpaRepository<OrderHasProduct, Long> {

    @Query("select ohp from OrderHasProduct ohp where ohp.amount > 1")
    List<OrderHasProduct> findByAmountGreaterThan(Integer greaterAmount);
}
