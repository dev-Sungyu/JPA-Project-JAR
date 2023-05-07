package com.app.projectjar.controller.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board/notice/*")
@RequiredArgsConstructor
public class NoticeController {

    @GetMapping("detail")
    public void noticeDetail() {}
    @GetMapping("list")
    public void noticeList() {}
}
