package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러는 이거 선언
public class HelloController {
    @GetMapping("hello") // 웹 어플리케이션에서 /hello라고 들어오면 이 매서드를 호출해줌 주소입력 8080/hello 치면 실행될 메서드
    public String hello(Model model){
        model.addAttribute("data", "hi!임");
        // 키는 데이터고, value는 hello이다
        return "hello"; // resoources:templates/'+{리턴이름}+'.html로 되는거임 -> hello.html 파일임
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name",required = false) String name, Model model){
        model.addAttribute("name", name); // key와 value 모델에 넘겨주는 과정
            return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString (@RequestParam("name") String name){
        return "hello " + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody // JSON으로 반환
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name); // 파라미터로 들어온 이름
        return hello;
    }
    static class Hello{
        private  String name;
// 프로퍼티 접근방식
        public String getName() { // 꺼낼때
            return name;
        }

        public void setName(String name) { // 넣을 때
            this.name = name;
        }
    }
}
