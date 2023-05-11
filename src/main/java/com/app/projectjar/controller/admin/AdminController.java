package com.app.projectjar.controller.admin;

import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.domain.page.PageDTO;
import com.app.projectjar.service.member.MemberService;
import com.app.projectjar.service.notice.NoticeService;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    private final SuggestService suggestService;
    private final NoticeService noticeService;
    private final MemberService memberService;


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
    @GetMapping("board/notice/detail/{noticeId}")
    public String adminNoticeDetail(Model model, @PathVariable("noticeId") Long noticeId) {
        NoticeDTO noticeDTO = noticeService.getNotice(noticeId);

        model.addAttribute("noticeDTOS", noticeDTO);

        return "admin/board/notice/detail";
    }
    @GetMapping("board/notice/list")
    public String adminNoticeList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
        Page<NoticeDTO> noticePage = noticeService.getAllNoticesWithPaging(page - 1);
        List<String> noticeTitles = noticePage.stream().map(NoticeDTO::getNoticeTitle).collect(Collectors.toList());
        model.addAttribute("pageDTO",new PageDTO(noticePage));
        model.addAttribute("noticeDTOS", noticePage.getContent());
        return "admin/board/notice/list";
    }
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
