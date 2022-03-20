package upce.fei.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import upce.fei.eshop.service.ShoppingCartService;

@Controller
public class OrderController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/checkout")
    public String checkout(Model model){
        shoppingCartService.checkout();
        return "checkout";
    }
}
