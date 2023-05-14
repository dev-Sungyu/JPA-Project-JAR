package com.app.projectjar.controller.board.diary.reply;

import com.app.projectjar.service.diary.reply.DiaryReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary/reply/*")
@RequiredArgsConstructor
public class DiaryReplyRestController {
    private final DiaryReplyService diaryReplyService;


}
