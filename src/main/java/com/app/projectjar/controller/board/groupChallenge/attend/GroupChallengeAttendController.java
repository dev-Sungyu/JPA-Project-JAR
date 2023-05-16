package com.app.projectjar.controller.board.groupChallenge.attend;

import com.app.projectjar.service.groupChallenge.attend.GroupChallengeAttendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groupChallenge/attend/*")
@RequiredArgsConstructor
public class GroupChallengeAttendController {

    private final GroupChallengeAttendService groupChallengeAttendService;

    @PostMapping("insert")
    public void insert(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId){
        groupChallengeAttendService.insertAttend(boardId,memberId);
    }

    @GetMapping("check")
    public Boolean attendCheck(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId) {
        return groupChallengeAttendService.attendCheck(boardId, memberId);
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId){
        groupChallengeAttendService.deleteAttend(boardId, memberId);
    }
}
