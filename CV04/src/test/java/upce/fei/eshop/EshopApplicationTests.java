package upce.fei.eshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import upce.fei.eshop.entity.*;
import upce.fei.eshop.repository.*;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EshopApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHasProductRepository orderHasProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProducerRepository producerRepository;


    @Test
    void createOrderTest() {
        Producer producer = new Producer();
        producer.setName("producer1");
        producer.setEmail("pr1@email.com");
        producer.setPhone("777777777");
        producerRepository.save(producer);

        Product product = new Product();
        product.setName("myProduct");
        product.setPrice(80.5);
        product.setProducer(producer);
        productRepository.save(product);

        User user = new User();
        user.setFirstName("Adam");
        user.setLastName("Novak");
        user.setEmail("adamnovak@email.com");
        userRepository.save(user);

        Order order = new Order();
        order.setStateEnum(StateEnum.NEW);
        order.setUser(user);
        orderRepository.save(order);

        OrderHasProduct orderHasProduct = new OrderHasProduct();
        orderHasProduct.setProduct(product);
        orderHasProduct.setOrder(order);
        orderHasProduct.setAmount(1);
        orderHasProduct.setPrice(product.getPrice()*orderHasProduct.getAmount());
        orderHasProductRepository.save(orderHasProduct);

        Optional<OrderHasProduct> result = orderHasProductRepository.findById(1L);
        Assertions.assertEquals(product.getName(), result.get().getProduct().getName());
        Assertions.assertEquals(user.getEmail(), result.get().getOrder().getUser().getEmail());
    }

    @Test
    void createProductTest() {
        Producer producer = new Producer();
        producer.setName("producer1");
        producer.setEmail("pr1@email.com");
        producer.setPhone("777777777");
        producerRepository.save(producer);

        Product product = new Product();
        product.setName("myProduct7");
        product.setPrice(80.5);
        product.setProducer(producer);
        productRepository.save(product);

        Product result = productRepository.findProductByNameContains("7");
        Assertions.assertEquals(product.getId(), result.getId());
    }

    @Test
    void createOrderAmountGreaterThanTest() {
        Producer producer = new Producer();
        producer.setName("producer2");
        producer.setEmail("pr2@email.com");
        producer.setPhone("777777777");
        producerRepository.save(producer);

        Product product = new Product();
        product.setName("myProduct");
        product.setPrice(80.5);
        product.setProducer(producer);
        productRepository.save(product);

        User user = new User();
        user.setFirstName("Adam");
        user.setLastName("Novak");
        user.setEmail("adamnovak@email.com");
        userRepository.save(user);

        Order order = new Order();
        order.setStateEnum(StateEnum.NEW);
        order.setUser(user);
        orderRepository.save(order);

        OrderHasProduct orderHasProduct = new OrderHasProduct();
        orderHasProduct.setProduct(product);
        orderHasProduct.setOrder(order);
        orderHasProduct.setAmount(5);
        orderHasProduct.setPrice(product.getPrice()*orderHasProduct.getAmount());
        orderHasProductRepository.save(orderHasProduct);

        OrderHasProduct orderHasProduct2 = new OrderHasProduct();
        orderHasProduct2.setProduct(product);
        orderHasProduct2.setOrder(order);
        orderHasProduct2.setAmount(2);
        orderHasProduct2.setPrice(product.getPrice()*orderHasProduct.getAmount());
        orderHasProductRepository.save(orderHasProduct);

        List<OrderHasProduct> result = orderHasProductRepository.findByAmountGreaterThan(2);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void createUserTest() {
        User user = new User();
        user.setFirstName("Petr");
        user.setLastName("Novy");
        user.setEmail("petrnovy@email.com");
        userRepository.save(user);

        Optional<User> result = userRepository.findByEmailContains("novy");
        Assertions.assertEquals(user.getId(), result.get().getId());
    }

    @Test
    void createProducerTest() {
        Producer producer = new Producer();
        producer.setName("myProducer");
        producer.setEmail("producer@email.com");
        producer.setPhone("123456789");
        producerRepository.save(producer);

        Optional<Producer> result = producerRepository.findProducerByNameContains("my");
        Assertions.assertEquals(producer.getId(), result.get().getId());
    }
}
