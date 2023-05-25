package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

 // @Repository
public class MemoryMemberRepository implements MeberRepository{
    // 저장을 해내야함 메모리이기 때문에
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0,1,2 키 값을 생성해준다고 보면됨
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // null 이나올거 같으면 이렇게 감싸주면됨
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    } // findAny()는 하나로도 찾는것? 끝까지 돌려서 없으면(null) Optional로 리턴
    // 루프를 map에서 돌면서 찾으면 반환해버림

    @Override
    public List<Member> findAll() { // 반환은 리스트 실무에서는 리스트를 많이 씀
        return new ArrayList<>(store.values()); // values가 멤버임
    }

    public void clearStore(){
        store.clear(); // 레파지토리를 비워버림
    }
}
