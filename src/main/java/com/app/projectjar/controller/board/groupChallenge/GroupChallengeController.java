package com.app.projectjar.controller.board.groupChallenge;

import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.groupChallenge.GroupChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board/challenge/group/*")
@RequiredArgsConstructor
public class GroupChallengeController {

    private final GroupChallengeService groupChallengeService;


    @GetMapping("content-list")
    @ResponseBody
    public Page<GroupChallengeDTO> getGroupChallengeList(@RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 9);
        return groupChallengeService.getGroupChallengeList(pageable);
    }

    @GetMapping("list")
    public void goToList(){ ; }

    @GetMapping("detail/{boardId}")
    public String goToDetail(Model model,  @PathVariable("boardId") Long boardId, @AuthenticationPrincipal UserDetail userDetail) {
        GroupChallengeDTO groupChallengeDTO = groupChallengeService.getGroupChallenge(boardId);

        model.addAttribute("groupChallengeDTO", groupChallengeDTO);
        model.addAttribute("userDetail", userDetail);

        return "/board/challenge/group/detail";
    }
}
