package com.app.projectjar.controller.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
public class MyPageController {

    @GetMapping("main")
    public void main(){}

    @GetMapping("personal-challenge")
    public void personal(){}

    @GetMapping("group-challenge")
    public void group(){}

    @GetMapping("inquire")
    public void inquire(){}

    @GetMapping("propsal")
    public void propsal(){}

    @GetMapping("like-list")
    public void like_list(){}

    @GetMapping("badge")
    public void badge(){}

    @GetMapping("modify")
    public void modify(){}

    @GetMapping("share")
    public void share(){}

   
}
