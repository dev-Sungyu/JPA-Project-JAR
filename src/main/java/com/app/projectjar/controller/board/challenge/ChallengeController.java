package com.app.projectjar.controller.board.challenge;

import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.service.personalChallenge.PersonalChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board/challenge/personal/*")
@RequiredArgsConstructor
public class ChallengeController {
    private final PersonalChallengeService personalChallengeService;


    @GetMapping("list")
    public void goToList(){
    }

    @GetMapping("list-content")
    @ResponseBody
    public Page<PersonalChallengeDTO> getList(@RequestParam("challengeStatus") String challengeStatus, @RequestParam(defaultValue = "0", name = "page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 12);
        return personalChallengeService.getListByChallengeStatus(challengeStatus, pageRequest);
    }


    @GetMapping("detail/{personalChallengeId}")
    public String goToDetail(Model model, @PathVariable("personalChallengeId") Long personalChallengeId) {
        PersonalChallengeDTO personalChallengeDTO = personalChallengeService.getPersonalChallenge(personalChallengeId);
        model.addAttribute("personalChallengeDTO", personalChallengeDTO);
        return "/board/challenge/personal/detail";
    }

}
