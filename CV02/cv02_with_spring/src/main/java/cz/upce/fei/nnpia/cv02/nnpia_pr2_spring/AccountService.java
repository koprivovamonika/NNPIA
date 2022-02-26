package cz.upce.fei.nnpia.cv02.nnpia_pr2_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    NotificationService notificationService;

    public void createAccount(){
        System.out.println("Account created.");
        notificationService.sendNotification("user", "account created");
    }
}
