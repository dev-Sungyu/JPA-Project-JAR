package com.app.projectjar.controller.admin;

import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.domain.page.PageDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.service.member.MemberService;
import com.app.projectjar.service.notice.NoticeService;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
@Slf4j
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

        model.addAttribute("noticeDTO", noticeDTO);

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
    @GetMapping("board/notice/modify/{noticeId}")
    public String adminNoticeModify(Model model, @PathVariable("noticeId") Long noticeId) {
        NoticeDTO noticeModifyDTO = noticeService.getNotice(noticeId);

        model.addAttribute("noticeDTO", noticeModifyDTO);
        return "/admin/board/notice/modify";
    }

    @PostMapping("board/notice/modify/{noticeId}")
    public RedirectView adminNoticeModifyPost(@PathVariable Long noticeId, String noticeTitle, String noticeContent) {
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .noticeContent(noticeContent)
                .noticeTitle(noticeTitle)
                .build();

        noticeService.updateNotice(noticeId, noticeDTO);
        return new RedirectView("/admin/board/notice/list");
    }

    @DeleteMapping("board/notice/delete")
    @ResponseBody
    public ResponseEntity<String> deleteNotices(@RequestBody List<Long> noticeIds) {
        noticeService.deleteNotices(noticeIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다.");
    }


    @GetMapping("board/notice/write")
    public void adminNoticeWrite(Model model) {
        model.addAttribute("noticeDTO", new NoticeDTO());
    }

    @PostMapping("board/notice/write")
    public String adminNoticeListPost(@ModelAttribute("noticeDTO") NoticeDTO noticeDTO) {
        noticeService.register(noticeDTO);
        return "redirect:/admin/board/notice/list";
    }





    @GetMapping("board/proposal/list")
    public String adminProposalList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<SuggestDTO> suggestPage = suggestService.getSuggestList(page - 1);
        List<String> suggestTitles = suggestPage.stream().map(SuggestDTO::getBoardTitle).collect(Collectors.toList());
        model.addAttribute("pageDTO",new PageDTO(suggestPage));
        model.addAttribute("suggestDTOS", suggestPage.getContent());
        return "admin/board/proposal/list";
    }
    @GetMapping("board/proposal/detail")
    public void adminProposalDetail() {

    }
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
