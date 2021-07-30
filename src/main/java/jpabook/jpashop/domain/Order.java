package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
//order 이 테이블명이 되기 때문에 테이블명 따로 지정
@Table(name ="orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //Order 이 member와의 양방향 관계에서 연관관계의 주인
    //연관관계의 주인은 외래키를 갖고있는 테이블로 정한다. (이해는 나중에)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id") //매핑할 칼럼
    private Member member;

    //cascade -> 값만 세팅해놓고 order만 persist 해도 CascadeType.All 이면 전부 persist 해줌
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]


    //==연관관계 편의 메서드==//
    public void setMemeber(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItems(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
