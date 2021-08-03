package jpabook.jpashop.repository.order.simplequery;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.SimpleOrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<Order> findAllWithMemberDelivery() {
        //fetch join
        // 한번에 객체를 다 채움
        return em.createQuery(
                        "select o from Order o" +
                                " join fetch o.member" +
                                " join fetch o.delivery d", Order.class)
                .getResultList();
    }

    public List<SimpleOrderQueryDto> findOrderDtos() {

        return em.createQuery(
                        "select new jpabook.jpashop.repository.SimpleOrderQueryDto(" +
                                "o.id , m.name , o.orderDate , o.status, d.address)" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", SimpleOrderQueryDto.class)
                .getResultList();

    }
}
