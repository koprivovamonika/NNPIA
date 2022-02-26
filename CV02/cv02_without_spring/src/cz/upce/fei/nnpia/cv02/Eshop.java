package cz.upce.fei.nnpia.cv02;

public class Eshop implements IEshop {
    AccountService accountService;
    OrderService orderService;

    public Eshop(AccountService accountService, OrderService orderService) {
        this.accountService = accountService;
        this.orderService = orderService;
    }

    public static void main(String args[]){
        IEshop eshop = DependencyInjector.getEshop();
        eshop.process();
    }

    @Override
    public void process(){
        accountService.createAccount();
        orderService.createOrder();
    }
}
