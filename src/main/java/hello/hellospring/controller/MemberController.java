package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

     @Autowired
    // 연결시켜줄때 생성자에서 쓰면 MemberController가 생성될때 스프링 빈에 등록되어있는 멤버 서비스 객체를 가져다 넣어줌
    // 디펜던시 인젝션 의존관계 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

    }

    @GetMapping("/members/new")
    public String createForm(){
         return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return  "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}
