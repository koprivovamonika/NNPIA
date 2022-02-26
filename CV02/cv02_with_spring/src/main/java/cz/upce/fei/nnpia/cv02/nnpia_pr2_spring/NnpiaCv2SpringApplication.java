package cz.upce.fei.nnpia.cv02.nnpia_pr2_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NnpiaCv2SpringApplication {

    @Autowired
    OrderService orderService;

    @Autowired
    AccountService accountService;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("cz.upce.fei.nnpia.cv02.nnpia_pr2_spring");
        context.getBean(NnpiaCv2SpringApplication.class).process();
    }

    private void process() {
        orderService.createOrder();
        accountService.createAccount();
    }

}
