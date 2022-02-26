package cz.upce.fei.nnpia.cv02.nnpia_pr2_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    NotificationService notificationService;

    public void createOrder(){
        System.out.println("Order created.");
        notificationService.sendNotification("user", "order created");
    }
}
