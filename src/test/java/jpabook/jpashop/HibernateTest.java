package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HibernateTest {

    @Autowired
    EntityManager entityManager;

    @Transactional
    @Test
    public void test(){

        Member member = new Member();

        System.out.println(member.getOrders().getClass());

        entityManager.persist(member);

        System.out.println(member.getOrders().getClass());
    }

}
