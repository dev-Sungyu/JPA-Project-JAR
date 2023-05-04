package com.app.projectjar.controller.board.suggest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/suggest/*")
public class SuggestController {

    @GetMapping("detail")
    public void detail() {

    }
}
