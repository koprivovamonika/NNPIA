package upce.fei.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import upce.fei.eshop.entity.*;
import upce.fei.eshop.repository.OrderHasProductRepository;
import upce.fei.eshop.repository.OrderRepository;
import upce.fei.eshop.repository.ProductRepository;
import upce.fei.eshop.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SessionScope
public class ShoppingCartService implements IShoppingCartService{

    private Map<Product, Integer> cart;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHasProductRepository orderHasProductRepository;

    public ShoppingCartService() {
        cart = new HashMap<>();
    }

    @Override
    public void add(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(cart.containsKey(product)){
            cart.replace(product, cart.get(product) + 1);
        }else{
            cart.put(product, 1);
        }

    }

    @Override
    public void remove(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(cart.containsKey(product)){
            if(cart.get(product) > 1){
                cart.replace(product, cart.get(product) - 1);
            }else{
                cart.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getCart() {
        return cart;
    }

    @Override
    public void checkout() {
        User user = new User();
        user.setFirstName("Adam");
        user.setLastName("Novak");
        user.setEmail("adamnovak@email.com");
        userRepository.save(user);

        Order order = new Order();
        order.setStateEnum(StateEnum.NEW);
        order.setUser(user);
        orderRepository.save(order);

        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            OrderHasProduct orderHasProduct = new OrderHasProduct();
            orderHasProduct.setOrder(order);
            orderHasProduct.setProduct(entry.getKey());
            orderHasProduct.setAmount(entry.getValue());
            orderHasProduct.setPrice(entry.getValue()*entry.getKey().getPrice());
            orderHasProductRepository.save(orderHasProduct);
        }

        cart.clear();
    }
}
