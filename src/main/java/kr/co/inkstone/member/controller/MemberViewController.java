package kr.co.inkstone.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberViewController {

    @GetMapping("/register")
    public String register() {
        return "member/register";
    }

    @GetMapping("/signup-success")
    public String signupSuccess() {
        return "member/signup-success";
    }
}
