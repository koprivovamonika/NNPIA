package cz.upce.fei.nnpia.cv02;

public class AccountService {
    NotificationService notificationService;

    public AccountService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void createAccount(){
        notificationService.sendNotification("user", "account created");
    }
}
