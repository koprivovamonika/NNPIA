package upce.fei.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upce.fei.eshop.service.ShoppingCartService;

@Controller
public class ShoppingCardController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/shopping-cart-add/{id}")
    public String shoppingCartAdd(@PathVariable Long id, Model model){
        shoppingCartService.add(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart-remove/{id}")
    public String shoppingCartRemove(@PathVariable Long id, Model model){
        shoppingCartService.remove(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart")
    public String showShoppingCart(Model model){
        model.addAttribute("shoppingCart", shoppingCartService.getCart());
        return "shopping-cart";
    }
}
