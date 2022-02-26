package cz.upce.fei.nnpia.cv02;

public class NotificationService {

    public NotificationService() {
        System.out.println("New instance of notification service.");
    }

    public void sendNotification(String to, String message){
        System.out.println("Sending notification to: " + to +" with body: " +message);
    }
}
