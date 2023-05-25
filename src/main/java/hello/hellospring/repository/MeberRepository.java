package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MeberRepository {
    Member save(Member member); // 회원이 저장소에 저장이됨
    Optional<Member> findById(Long id); // 레파지토리에서 id를 찾아올 수 있음
    Optional<Member> findByName(String name); // id와 같은 기능
    List<Member> findAll(); // 지금까지 저장된 회원리스트 반환
}
