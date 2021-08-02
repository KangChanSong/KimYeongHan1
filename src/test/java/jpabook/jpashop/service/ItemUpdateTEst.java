package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemUpdateTEst {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception{
        Book book = em.find(Book.class, 1L);

        //TX
        book.setName("asdbgfs");

        //TX commit
        //변경 감지 후 트랜잭션 커밋될때 반영 (Dirty Checking)
    }

}
