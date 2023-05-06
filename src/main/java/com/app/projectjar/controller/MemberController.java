package com.app.projectjar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("login")
    public void login(){}

    @GetMapping("sign-in")
    public void signIn(){}

    @GetMapping("sign-in-OAuth")
    public void Oauth(){}

    @GetMapping("password")
    public void password(){}

    @GetMapping("change-password")
    public void changePassword(){}

    @GetMapping("account-confirm")
    public void account(){}

    @GetMapping("phone-certification")
    public void phone(){}


}
