package kr.co.inkstone.login.controller;

import kr.co.inkstone.login.domain.dto.request.LoginRequestDto;
import kr.co.inkstone.member.domain.entity.Member;
import kr.co.inkstone.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;


    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto loginDto, Model model, RedirectAttributes redirectAttributes) {
        try{
            Member member = memberService.findMemberByLoginId(loginDto);
            model.addAttribute("name", member.getName());
            return "login/login-success";

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",  e.getMessage());
            return "redirect:/";
        }
    }
}
