package cz.upce.fei.nnpia.cv02;

public class OrderService {
    NotificationService notificationService;

    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void createOrder(){
        notificationService.sendNotification("user", "order created");
    }
}
