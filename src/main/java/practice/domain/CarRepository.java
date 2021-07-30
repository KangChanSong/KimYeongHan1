package practice.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CarRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Car car){
        em.persist(car);
        return car.getId();
    }

    public Car get(Long id){
        return em.find(Car.class, id);
    }
}
