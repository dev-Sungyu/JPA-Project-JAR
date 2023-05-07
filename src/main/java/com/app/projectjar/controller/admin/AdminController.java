package com.app.projectjar.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    @GetMapping("challenge/detail")
    public void adminChallengeDetail() {}
    @GetMapping("challenge/list")
    public void adminChallengeList() {}
    @GetMapping("challenge/modify")
    public void adminChallengeModify() {}
    @GetMapping("challenge/write")
    public void adminChallengeWrite() {}
    @GetMapping("inquiry/answer")
    public void adminInquiryAnswer() {}
    @GetMapping("inquiry/detail")
    public void adminInquiryDetail() {}
    @GetMapping("inquiry/list")
    public void adminInquiryList() {}
    @GetMapping("inquiry/modify")
    public void adminInquiryModify() {}
    @GetMapping("member/detail")
    public void adminMemberDetail() {}
    @GetMapping("member/list")
    public void adminMemberList() {}
    @GetMapping("member/modify")
    public void adminMemberModify() {}
    @GetMapping("notice/detail")
    public void adminNoticeDetail() {}
    @GetMapping("notice/list")
    public void adminNoticeList() {}
    @GetMapping("notice/modify")
    public void adminNoticeModify() {}
    @GetMapping("notice/write")
    public void adminNoticeWrite() {}
    @GetMapping("proposal/detail")
    public void adminProposalDetail() {}
    @GetMapping("proposal/list")
    public void adminProposalList() {}
    @GetMapping("proposal/modify")
    public void adminProposalModify() {}
    @GetMapping("diary/list")
    public void adminDiaryList() {}
    @GetMapping("diary/detail")
    public void adminDiaryDetail() {}


}
