package com.app.projectjar.controller.mypage;

import com.app.projectjar.domain.calendar.CalendarDTO;
import com.app.projectjar.domain.diary.DiaryDTO;
<<<<<<< HEAD
import com.app.projectjar.domain.diary.DiaryLikeDTO;
=======
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
>>>>>>> master
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.domain.suggest.SuggestLikeDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.diary.DiaryService;
import com.app.projectjar.service.diary.like.DiaryLikeService;
import com.app.projectjar.service.inquire.InquireService;
import com.app.projectjar.service.member.MemberService;
import com.app.projectjar.service.mypage.MyPageService;
import com.app.projectjar.service.suggest.SuggestService;
import com.app.projectjar.service.suggest.like.SuggestLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MyPageService myPageService;
    private final MemberService memberService;
    private final InquireService inquireService;
    private final SuggestService suggestService;
    private final DiaryService diaryService;
    private final SuggestLikeService suggestLikeService;
    private final DiaryLikeService diaryLikeService;

    @GetMapping("main")
    public void main(@AuthenticationPrincipal UserDetail userDetail, Model model, HttpSession session){
//        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//        MemberDTO memberDTO = (MemberDTO) userDetail.("member");
        log.info("@@@@@@@@@@@@ main Controller @@@@@@@@@@@@@");
        log.info("@@@@@@@@@@@@" + userDetail);
//        memberDTO = memberService.getMember(memberDTO.getId());
//        model.addAttribute("member", memberDTO);
//        List<CalendarDTO> calendarDTOS = myPageService.getCalendarDTO(memberDTO.getId());
        List<CalendarDTO> calendarDTOS = myPageService.getCalendarDTO(userDetail.getId());
        model.addAttribute("calendarDTOS",calendarDTOS);
        model.addAttribute("userDetail", userDetail);
    }

    @PostMapping("register")
    public RedirectView register(@ModelAttribute("diaryDTO") DiaryDTO diaryDTO,@AuthenticationPrincipal UserDetail userDetail){
        log.info("@@@@@@@@ register Controller @@@@@@@@");
        Long memberId = userDetail.getId();
        myPageService.registerDiary(diaryDTO, memberId);
        return new RedirectView("/mypage/main?check=true");
    }

    @DeleteMapping("diary-detail")
    @ResponseBody
    public DiaryDTO getDiaryDTO(@RequestParam("diaryId")Long diaryId) {
        return myPageService.getDiary(diaryId);
    }

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

    @DeleteMapping("delete-main/{boardId}")
    @ResponseBody
    public void deleteMain(@PathVariable("boarId") Long diaryId){ diaryService.delete(diaryId);}

    @GetMapping("personal-challenge")
    public void personal(Model model, @AuthenticationPrincipal UserDetail userDetail){
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("challenge-content")
    @ResponseBody
    public Page<PersonalChallengeDTO> getPersonalChallengeDTOS(@RequestParam("challengeStatus") String challengeStatus, @RequestParam("memberId") Long memberId, @RequestParam("page") int page){
        PageRequest pageRequest = PageRequest.of(page, 9);
        return myPageService.getChallengeList(challengeStatus, memberId, pageRequest);
    }

    @GetMapping("group-challenge")
    public void group(Model model, @AuthenticationPrincipal UserDetail userDetail){model.addAttribute("userDetail", userDetail);}

    @GetMapping("group-challenge-content")
    @ResponseBody
    public Page<GroupChallengeDTO> getGroupChallengeDTOS(@RequestParam("challengeStatus") String challengeStatus, @RequestParam("memberId") Long memberId, @RequestParam("page") int page){
        PageRequest pageRequest = PageRequest.of(page, 9);
        return myPageService.getGroupChallengeList(challengeStatus, memberId, pageRequest);
    }

    @GetMapping("inquire")
    public void goToInquire(@AuthenticationPrincipal UserDetail userDetail,Model model){
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("inquire-list")
    @ResponseBody
    public Page<InquireDTO> inquire(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<InquireDTO> inquireDTOS = inquireService.getInquireForMemberIdList(pageable, id);
        return inquireDTOS;
    }

    @DeleteMapping("delete-inquire/{boardId}")
    @ResponseBody
    public void deleteInquire(@PathVariable("boardId") Long boardId){
        inquireService.deleteInquire(boardId);
    }

    @GetMapping("propsal")
    public void goToPropsal(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("propsal-list")
    @ResponseBody
    public Page<SuggestDTO> propsal(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<SuggestDTO> suggestDTOS = suggestService.getSuggestForMemberIdList(pageable, id);
        return suggestDTOS;
    }

    @DeleteMapping("delete-suggest/{boardId}")
    @ResponseBody
    public void deletePropsal(@PathVariable("boardId") Long boardId){
        suggestService.delete(boardId);
    }


    @GetMapping("share")
    public void goToShare(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("share-list")
    @ResponseBody
    public Page<DiaryDTO> share(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<DiaryDTO> diaryDTOS = diaryService.getDiaryForMemberIdList(pageable, id);
        log.info("================");
        log.info(String.valueOf(id));
        log.info(String.valueOf(page));
        diaryDTOS.forEach(suggestDTO -> log.info(suggestDTO.toString())); // 잘 찍힘
        log.info("================");
        return diaryDTOS;
    }

    @DeleteMapping("delete-diary/{boardId}")
    @ResponseBody
    public void deleteShare(@PathVariable("boardId") Long boardId){
        diaryService.delete(boardId);
    }

    @GetMapping("suggest-like-list")
    public void goToSuggestLike(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("suggest-like-list-content")
    @ResponseBody
    public Page<SuggestLikeDTO> suggestLikeList(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<SuggestLikeDTO> suggestLikeDTOS = suggestLikeService.getLikeSuggestForMemberIdList(pageable, id);
        return suggestLikeDTOS;
    }

    @GetMapping("diary-like-list")
    public void goToDiaryLike(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("diary-like-list-content")
    @ResponseBody
    public Page<DiaryLikeDTO> diaryLikeList(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<DiaryLikeDTO> diaryLikeDTOS = diaryLikeService.getLikeDiaryForMemberIdList(pageable, id);
        return diaryLikeDTOS;
    }


    @GetMapping("modify")
    public void modify(){}


   
}
