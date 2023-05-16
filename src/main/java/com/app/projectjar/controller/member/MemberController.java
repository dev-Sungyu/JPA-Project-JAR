package com.app.projectjar.controller.member;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Random;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Slf4j
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
    public void goToLoginForm() {;}

    @GetMapping("join-OAuth")
    public void Oauth(){}

    @GetMapping("password")
    public void password(){}

    @GetMapping("change-password")
    public void changePassword(){}

    @GetMapping("account-confirm")
    public void account(){}

//    @GetMapping("phone-certification")
//    public void phone(){}

    @GetMapping("logout")
    public void goToLogOut(){;}

    //    인증번호 보내기
    @PostMapping("sendCode")
    @ResponseBody
    public String sendCode(String memberPhone) {
        Random random = new Random();

        String code = "";
        for (int i = 0; i < 6; i++) {
            String number = Integer.toString(random.nextInt(10));
            code += number;
        }

        memberService.checkSMS(memberPhone, code);
        return code;
    }


}
