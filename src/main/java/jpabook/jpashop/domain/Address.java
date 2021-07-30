package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //JPA spec 에서 protected 까지 허용
    protected Address(){}

    //값 타입은 변경 불가능하게 설계해야한다.
    //생성자를 이용해 값을 모두 초기화해 읽기전용으로 만들자
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
