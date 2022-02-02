package com.koreait.springbootboard.user;

import com.koreait.springbootboard.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService service;

    //@ModelAttribute : 사용자가 요청시 전달하는 값을 오브젝트형태로 매핑해준다. 밑엔지워도됨
    @GetMapping("/login")
    public void login(@ModelAttribute UserEntity userEntity) {}

    @PostMapping("/login")
    public String loginProc(UserEntity entity){
        int rs = service.login(entity);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutProc(HttpSession hs){
        hs.invalidate();
        return "redirect:/";
    }

    @GetMapping("/join")
    public void join(@ModelAttribute UserEntity userEntity) {}

    @PostMapping("/join")
    public String joinProc(UserEntity userEntity){
        int rs = service.join(userEntity);
        return "redirect:login"; //슬러시 안붙이면 끝에 주소만 바뀐다 /user/login으로 이동
    }
}
