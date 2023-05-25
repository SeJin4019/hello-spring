package hello.hellospring;

import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // 둘다 스프링 빈에 등록
    @Bean
    // 생성자가 있기 때문에 인자가 필요 Repository(등록되어있는)가 필요 그래서 생성
    public MemberService memberService(){
        return new MemberService(memoryMemberRepository());
        // 이 부분이 컴포넌트 스캔에서 autowired 역할임
    }

    @Bean
    public  MemoryMemberRepository memoryMemberRepository(){
        return new MemoryMemberRepository();
    }
}
