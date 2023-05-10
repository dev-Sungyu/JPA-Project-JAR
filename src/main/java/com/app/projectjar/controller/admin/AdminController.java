package com.app.projectjar.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    @GetMapping("board/challenge/detail")
    public void adminChallengeDetail() {}
    @GetMapping("board/challenge/list")
    public void adminChallengeList() {}
    @GetMapping("board/challenge/modify")
    public void adminChallengeModify() {}
    @GetMapping("board/challenge/write")
    public void adminChallengeWrite() {}
    @GetMapping("board/inquiry/answer")
    public void adminInquiryAnswer() {}
    @GetMapping("board/inquiry/detail")
    public void adminInquiryDetail() {}
    @GetMapping("board/inquiry/list")
    public void adminInquiryList() {}
    @GetMapping("board/inquiry/modify")
    public void adminInquiryModify() {}
    @GetMapping("member/detail")
    public void adminMemberDetail() {}
    @GetMapping("member/list")
    public void adminMemberList() {}
    @GetMapping("member/modify")
    public void adminMemberModify() {}
    @GetMapping("board/notice/detail")
    public void adminNoticeDetail() {}
    @GetMapping("board/notice/list")
    public void adminNoticeList() {}
    @GetMapping("board/notice/modify")
    public void adminNoticeModify() {}
    @GetMapping("board/notice/write")
    public void adminNoticeWrite() {}
    @GetMapping("board/proposal/detail")
    public void adminProposalDetail() {}
    @GetMapping("board/proposal/list")
    public void adminProposalList() {}
    @GetMapping("board/proposal/modify")
    public void adminProposalModify() {}
    @GetMapping("board/diary/list")
    public void adminDiaryList() {}
    @GetMapping("board/diary/detail")
    public void adminDiaryDetail() {}
    @GetMapping("board/diary/modify")
    public void adminDiaryModify() {}
    @GetMapping("board/groupChallenge/detail")
    public void adminGroupChallengeDetail() {}
    @GetMapping("board/groupChallenge/list")
    public void adminGroupChallengeList() {}
    @GetMapping("board/groupChallenge/modify")
    public void adminGroupChallengeModify() {}
    @GetMapping("board/groupChallenge/write")
    public void adminGroupChallengeWrite() {}


}
