package com.app.projectjar.controller.member;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("join")
    public String goToJoinForm(MemberDTO memberDTO){
        return "/member/join";
    }

    @PostMapping("join")
    public RedirectView join(MemberDTO memberDTO){
        memberService.join(memberDTO, passwordEncoder);
        return new RedirectView("/member/login");
    }

    @GetMapping("login")
    public void goToLoginForm() {};

//    @GetMapping("login")
//    public void login(){}
//
//    @GetMapping("sign-in")
//    public void signIn(){}

//    @GetMapping("sign-in-OAuth")
//    public void Oauth(){}

    @GetMapping("password")
    public void password(){}

    @GetMapping("change-password")
    public void changePassword(){}

    @GetMapping("account-confirm")
    public void account(){}

    @GetMapping("phone-certification")
    public void phone(){}


}
