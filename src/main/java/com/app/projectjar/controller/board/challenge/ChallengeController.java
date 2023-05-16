package com.app.projectjar.controller.board.challenge;

import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.repository.personalChallenge.PersonalChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/challenge/personal/*")
@RequiredArgsConstructor
public class ChallengeController {
    private final PersonalChallengeRepository personalChallengeRepository;


    @GetMapping("list")
    public void goToList(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("detail")
    public void goToDetail(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDetail", userDetail);
    }
}
