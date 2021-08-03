package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;
    
    //양방향 연관관계에서는 하나를 JsonIgnore 해줘야함
    //안그러면 무한루프돔
    @JsonIgnore
    //읽기전용. 매핑될 거울일 뿐이라는 것을 명시
    @OneToMany(mappedBy = "member")
    //컬렉션은 하이버네이트가 내부적으로 별도의 클래스로 감싼다.
    //만약 영속화된 클래스의 컬렉션 타입의 멤버가 수정된다면 하이버네이트의 메커니즘을 사용할 수 없게 된다.
    private List<Order> orders = new ArrayList<>();

}
