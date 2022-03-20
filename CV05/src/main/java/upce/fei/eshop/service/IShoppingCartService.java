package upce.fei.eshop.service;

import upce.fei.eshop.entity.Product;

import java.util.Map;

public interface IShoppingCartService {
    void add(Long id);
    void remove(Long id);
    Map<Product, Integer> getCart();

    void checkout();
}
