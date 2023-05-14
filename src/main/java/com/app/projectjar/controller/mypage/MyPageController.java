package com.app.projectjar.controller.mypage;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.diary.DiaryService;
import com.app.projectjar.service.inquire.InquireService;
import com.app.projectjar.service.member.MemberService;
import com.app.projectjar.service.suggest.SuggestService;
import com.app.projectjar.type.BadgeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MemberService memberService;
    private final InquireService inquireService;
    private final SuggestService suggestService;
    private final DiaryService diaryService;

    @GetMapping("main")
    public void main(){}

    @GetMapping("badge")
    public void badge(@AuthenticationPrincipal UserDetail userDetail, Model model){
        Long memberId = userDetail.getId();
        MemberDTO memberDTO = memberService.getMember(memberId);
        int totalCount = memberService.getMemberBadgeCount(memberId);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("memberDTO", memberDTO);
        log.info(memberId.toString());
        memberService.updateBadge(memberId);
    }

    @GetMapping("personal-challenge")
    public void personal(){}

    @GetMapping("group-challenge")
    public void group(){}

    @GetMapping("inquire")
    public void goToInquire(){}

    @GetMapping("inquire-list")
    @ResponseBody
    public Page<InquireDTO> inquire(@AuthenticationPrincipal UserDetail userDetail, @RequestParam(defaultValue = "0", name = "page") int page){
        Long memberId = userDetail.getId();
        PageRequest pageable = PageRequest.of(page, 6);
        Page<InquireDTO> inquireDTOS = inquireService.getInquireForMemberIdList(pageable, memberId);
        return inquireDTOS;
    }

    @GetMapping("propasal")
    public void goToPropasal(){}

    @GetMapping("propsal-list")
    @ResponseBody
    public Page<SuggestDTO> propsal(@AuthenticationPrincipal UserDetail userDetail, @RequestParam(defaultValue = "0", name = "page") int page){
        Long memberId = userDetail.getId();
        PageRequest pageable = PageRequest.of(page, 6);
        Page<SuggestDTO> suggestDTOS = suggestService.getSuggestForMemberIdList(pageable, memberId);
        return suggestDTOS;
    }

    @GetMapping("share")
    public void goToshare(){}

    @GetMapping("share-list")
    public Page<DiaryDTO> share(@AuthenticationPrincipal UserDetail userDetail, @RequestParam(defaultValue = "0", name = "page") int page){
        Long memberId = userDetail.getId();
        PageRequest pageable = PageRequest.of(page, 6);
        Page<DiaryDTO> diaryDTOS = diaryService.getDiaryForMemberIdList(pageable, memberId);
        return diaryDTOS;
    }

    @GetMapping("suggest-like-list")
    public void suggestLikelist(){}

    @GetMapping("diary-like-list")
    public void diarylikelist(){}


    @GetMapping("modify")
    public void modify(){}


   
}
