package upce.fei.eshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import upce.fei.eshop.entity.*;
import upce.fei.eshop.repository.*;
import upce.fei.eshop.service.ShoppingCartService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ShoppingCartTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;


    @Test
    void addOneToShoppingCart() {
        Producer producer = new Producer();
        producer.setName("producer2");
        producer.setEmail("pr2@email.com");
        producer.setPhone("777777777");
        producerRepository.save(producer);

        Product product = new Product();
        product.setProductName("myProduct");
        product.setPrice(80.5);
        product.setProducer(producer);
        productRepository.save(product);

        List<Product> all = productRepository.findAll();
        Long id =all.get(0).getId();

        shoppingCartService.add(id);

        Assertions.assertEquals(shoppingCartService.getCart().size(), 1);
        Assertions.assertEquals(shoppingCartService.getCart().get(all.get(0)), 1);
        Assertions.assertTrue(shoppingCartService.getCart().containsKey(all.get(0)));

        shoppingCartService.add(id);
        Assertions.assertEquals(shoppingCartService.getCart().get(all.get(0)), 2);

        shoppingCartService.add(id);
        Assertions.assertEquals(shoppingCartService.getCart().get(all.get(0)), 3);

        shoppingCartService.remove(id);
        Assertions.assertEquals(shoppingCartService.getCart().get(all.get(0)), 2);
    }
}
