package com.example.nnpia_cv03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Locale;

@SpringBootApplication
public class NnpiaCv03Application {

    public static void main(String[] args) {
        SpringApplication.run(NnpiaCv03Application.class, args);
    }


    @Controller
    @RequestMapping("user")
    public class GreetingController{

        @Autowired
        private SessionCounterService sessionCounterService;

        @Autowired
        private CounterService counterService;

        @RequestMapping(value = "/greeting/{userName}/{password}")
        public String listUsersInvoices(@PathVariable("userName") String userName,@PathVariable("password") String password, Model model){
            counterService.add();
            User user = new User(userName,password);
            model.addAttribute("user", user);
            model.addAttribute("counter", counterService.get());
            return "greeting";
        }

        @GetMapping(value = "/welcome")
        public String requestGreeting(@RequestParam(name = "username", required = true) String userName, @RequestParam(name = "pswd", required = false, defaultValue = "1234") String password, Model model){
            sessionCounterService.add();
            User user = new User(userName,password);
            model.addAttribute("user", user);
            model.addAttribute("counter", sessionCounterService.get());
            return "greeting";
        }

        @PostMapping(value = "/welcome1")
        public String requestGreeting2(@RequestParam(name = "username", required = true) String userName, @RequestParam(name = "pswd", required = false, defaultValue = "1234") String password, Model model){
            sessionCounterService.add();
            User user = new User(userName,password);
            model.addAttribute("user", user);
            model.addAttribute("counter", sessionCounterService.get());
            return "greeting";
        }

    }

}

