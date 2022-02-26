package cz.upce.fei.nnpia.cv02;

public class DependencyInjector {
    private static OrderService orderService;
    private static AccountService accountService;
    private static NotificationService notificationService;

    private static NotificationService getNotificationService(){
        if(notificationService == null){
            notificationService = new NotificationService();
        }
        return notificationService;
    }

    private static OrderService getOrderService(){
        if(orderService == null){
            orderService = new OrderService(getNotificationService());
        }
        return orderService;
    }

    private static AccountService getAccountService(){
        if(accountService == null){
            accountService = new AccountService(getNotificationService());
        }
        return accountService;
    }


    public static IEshop getEshop() {
        return new Eshop(getAccountService(), getOrderService());
    }
}
