package kr.co.inkstone.member.controller;

import kr.co.inkstone.member.service.MemberService;
import kr.co.inkstone.security.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberViewController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String register() {
        return "member/register";
    }

    @GetMapping("/signup-success")
    public String signupSuccess() {
        return "member/signup-success";
    }

    @GetMapping("/edit-profile")
    public String editProfile(@AuthenticationPrincipal CustomMemberDetails userDetails, Model model) {
         model.addAttribute("name", userDetails.getName());
         model.addAttribute("phone", userDetails.getPhone());
        return "member/edit-profile";
    }
}
