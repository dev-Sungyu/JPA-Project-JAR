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
    public void changePassword(){}

    @GetMapping("login")
    public void login(){}

    @GetMapping("password")
    public void password(){}

    @GetMapping("phone-certification")
    public void phone(){}

    @GetMapping("sign-in")
    public void signIn(){}

    @GetMapping("sign-in-OAuth")
    public void Oauth(){}

}
