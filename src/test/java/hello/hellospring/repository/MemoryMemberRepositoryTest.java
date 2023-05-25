package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 끝날때마다 repository가 클리어 되어야함
    @AfterEach // 한 메서드가 끝날때마다 어떤 동작을 하는 콜백메서드 호출이됨
    // 순서가 관계 없어져서 오류가 생기지 않음 공용 데이터를 깨끗하게 지워줘야함
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("sejin");

        repository.save(member); // reposiroy에 저장

        Member result = repository.findById(member.getId()).get(); // 옵셔널은 get으로 껄낼 수 있음
        // Assertions.assertEquals(result, member);
        // 멤버가 result랑 같다
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("sejin1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sejin2");
        repository.save(member2);

        // get()을 쓰면 옵셔널까서 쓸 수 있음
        Member result = repository.findByName("sejin1").get();

        assertThat(result).isEqualTo(member2);

    }

    @Test
    public void finAll(){
        Member member1 = new Member();
        member1.setName("sejin1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sejin2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}


