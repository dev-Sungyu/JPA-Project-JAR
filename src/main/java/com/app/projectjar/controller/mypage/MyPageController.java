package com.app.projectjar.controller.mypage;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.member.MemberService;
import com.app.projectjar.type.BadgeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MemberService memberService;

    @GetMapping("main")
    public void main(){}

    @GetMapping("personal-challenge")
    public void personal(){}

    @GetMapping("group-challenge")
    public void group(){}

    @GetMapping("inquire")
    public void inquire(){}

    @GetMapping("propsal")
    public void propsal(){}

    @GetMapping("suggest-like-list")
    public void suggestLikelist(){}

    @GetMapping("diary-like-list")
    public void diarylikelist(){}

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

    @GetMapping("modify")
    public void modify(){}

    @GetMapping("share")
    public void share(){}

   
}
