package kr.co.inkstone.login.controller;


import kr.co.inkstone.security.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {



    @GetMapping("/success")
    public String login(@AuthenticationPrincipal CustomMemberDetails userDetails, Model model) {
        model.addAttribute("name", userDetails.getName());
        return "login/login-success";
    }
}
