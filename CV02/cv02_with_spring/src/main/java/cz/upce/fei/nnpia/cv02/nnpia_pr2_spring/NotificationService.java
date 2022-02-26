package cz.upce.fei.nnpia.cv02.nnpia_pr2_spring;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(String to, String message){
        System.out.println("Notification sent to: " +to+" with body: "+message);
    }
}
