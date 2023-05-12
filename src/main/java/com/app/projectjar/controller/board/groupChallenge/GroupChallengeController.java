package com.app.projectjar.controller.board.groupChallenge;

import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.service.groupChallenge.GroupChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board/group-challenge/*")
@RequiredArgsConstructor
public class GroupChallengeController {

    private final GroupChallengeService groupChallengeService;


    @GetMapping("list")
    @ResponseBody
    public Page<GroupChallengeDTO> getGroupChallengeList(@RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 9);
        return groupChallengeService.getGroupChallengeList(pageable);
    }

}
