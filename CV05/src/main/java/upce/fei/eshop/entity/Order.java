package upce.fei.eshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "orderForm")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StateEnum stateEnum;

    @OneToMany(mappedBy = "id")
    private Set<OrderHasProduct> orderHasProducts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Set<OrderHasProduct> getOrderHasProducts() {
        return orderHasProducts;
    }

    public void setOrderHasProducts(Set<OrderHasProduct> orderHasProducts) {
        this.orderHasProducts = orderHasProducts;
    }

    public StateEnum getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(StateEnum stateEnum) {
        this.stateEnum = stateEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
