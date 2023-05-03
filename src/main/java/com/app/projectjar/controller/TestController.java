package com.app.projectjar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("main")
    public void testMain() {
    }
}
