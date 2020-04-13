package lozm.entity.orders;

import lombok.Getter;
import lozm.code.OrderStatus;
import lozm.vo.orders.OrdersVo;
import lozm.entity.BaseEntity;
import lozm.entity.delivery.Delivery;
import lozm.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(schema = "LOZM", name = "ORDERS")
public class Orders extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    @Column(name = "ORDER_DT")
    private LocalDateTime orderDt;

    @Column(name = "STATUS")
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrdersItem> ordersItems;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    public void insertOrders(OrdersVo ordersVo, User user) {
        this.orderDt = LocalDateTime.now();
        this.status = OrderStatus.PREPARATION;
        this.user = user;
    }

    public void updateOrders(OrdersVo ordersVo) {
        this.status = OrderStatus.valueOf(ordersVo.getStatus());
        this.setBaseEntity("", ordersVo.getFlag());
    }
}