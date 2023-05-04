package com.app.projectjar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class adminController {
    @GetMapping("challenge/detail")
    public void adminChallengeDetail() {
    }
    @GetMapping("challenge/list")
    public void adminChallengeList() {
    }

}
