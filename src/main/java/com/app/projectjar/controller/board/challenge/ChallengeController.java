package com.app.projectjar.controller.board.challenge;

import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.repository.personalChallenge.PersonalChallengeRepository;
import com.app.projectjar.service.personalChallenge.PersonalChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/board/challenge/personal/*")
@RequiredArgsConstructor
public class ChallengeController {
    private final PersonalChallengeService personalChallengeService;


    @GetMapping("list")
    public void goToList(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("list-content")
    @ResponseBody
    public Page<PersonalChallengeDTO> getList(@RequestParam("challengeStatus") String challengeStatus, @RequestParam(defaultValue = "0", name = "page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 12);
        return personalChallengeService.getListByChallengeStatus(challengeStatus, pageRequest);
    }


    @GetMapping("detail")
    public void goToDetail(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDetail", userDetail);
    }
}
