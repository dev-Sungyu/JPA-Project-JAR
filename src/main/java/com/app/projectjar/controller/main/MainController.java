package com.app.projectjar.controller.main;

import com.app.projectjar.domain.board.BoardSearchDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.search.board.GroupChallengeSearch;
import com.app.projectjar.search.board.SuggestSearch;
import com.app.projectjar.service.groupChallenge.GroupChallengeService;
import com.app.projectjar.service.groupChallenge.reply.GroupChallengeReplyService;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final SuggestService suggestService;

    private final GroupChallengeService groupChallengeService;

    private final GroupChallengeReplyService groupChallengeReplyService;

    @GetMapping("")
    public void main(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("/list-content")
    @ResponseBody
    public List<SuggestDTO> listContent() {
        // suggestService를 사용하여 리스트를 가져오는 로직 작성
        List<SuggestDTO> list = suggestService.findAllWithoutPaging_QueryDsl(); // 적절한 로직으로 대체해야 합니다.
        return list;
    }

    @GetMapping("search")
    public void boardSearch(@RequestParam String search) {
        BoardSearchDTO searchDTO = new BoardSearchDTO();

        searchDTO.getGroupChallengeSearch().setBoardTitle(search);
        searchDTO.getSuggestSearch().setBoardTitle(search);
        log.info(searchDTO.toString());

        groupChallengeService.findBoardSearch_QueryDSL((List<BoardSearchDTO>) searchDTO);

    }

    @GetMapping("service-introduction")
    public void serviceIntroduce(){

    }
    @GetMapping("faq")
    public void faq(){

    }

}
