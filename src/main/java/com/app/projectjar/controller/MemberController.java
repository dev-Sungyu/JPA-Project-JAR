package com.app.projectjar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("account-confirm")
    public void account(){}

    @GetMapping("change-password")
    public String changePassword(){
        return "/change-password";
    }

    @GetMapping("login")
    public void login(){}

    @GetMapping("password")
    public void password(){}

    @GetMapping("phone-certification")
    public String phone(){
        return "/phone-certification";
    }

    @GetMapping("sign-in")
    public String signIn(){
        return "/sign-in";
    }

    @GetMapping("sign-in-OAuth")
    public String Oauth(){
        return "/sign-in-OAuth";
    }

}
