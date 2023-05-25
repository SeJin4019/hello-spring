package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// @Service
// 스프링이 생성할때 컨테이너에 등록하면서 생성자 호출 그때 Autowired가 보이면 memberRepositoty가 필요하구나 생각함
public class MemberService {

    // private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private final MemoryMemberRepository memberRepository;

    //외부에서 넣어주는 DI
    // @Autowired
    public MemberService(MemoryMemberRepository memberRepository) { // 외부에 넣어주도록
        this.memberRepository = memberRepository;
    }

    /**
     *회원 가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 x commend + option + v
        // control + t 리팩토링에 대한게 나옴
        validateDuplicateMember(member); // 중복 회원 검사 매서드 추출
        memberRepository.save(member); // 통과하면 저장
        return member.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 이쪽은 optional로 반환되기 때문에
            .ifPresent(m ->{ // 값이 있으면 null이 아니고 값이 있으면 이 매서드 사용가능
                throw new IllegalArgumentException("이미 존재하는 회원입니다.");
            });
    }

}
