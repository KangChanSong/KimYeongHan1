package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    //접근핡 수 없기 때문에 테스트 하기 어려움
    //@Autowired
    //컴파일시 체크할 수 있기 때문에 final 로 선언하는게 좋음
    private final MemberRepository memberRepository;

//    //사실 세터를 호출할 일 이 잘 없음
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    
    //생성자가 하나만 있는 경우에는 스프링이 자동으로 오토와이어링해줌
    //@Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    /**
    *회원가입
    */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        //em.persist 할때 디비에 들어간 시점이 아니어도 id 값을 채워줌
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        //EXCEPTION
        //실무에서는 여러 사용자가 동시에 같은 이름으로 회원가입할 수 도 있음
        //따라서 DB에서 name 속성을 unique 로 지정하는게 좋음
        List<Member> findMemebers = memberRepository.findByNames(member.getName());
        if(!findMemebers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
    
    //회원 전체 조회

}
