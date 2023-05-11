package com.app.projectjar.controller.board.suggest;

import com.app.projectjar.domain.page.PageDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/board/suggest/*")
@RequiredArgsConstructor
@Slf4j
public class SuggestController {
    private final SuggestService suggestService;

    @GetMapping("write")
    public void goToWriteForm(SuggestDTO suggestDTO) { }

    @PostMapping("write")
    public RedirectView write(@ModelAttribute("suggestDTO") SuggestDTO suggestDTO, @AuthenticationPrincipal UserDetail userDetail) {

        Long memberId = userDetail.getMemberId();
        suggestService.register(suggestDTO, memberId);
        if(suggestDTO.getBoardType().name().equals("GROUP")){
            return new RedirectView("/board/suggest/list/group");
        }
        return new RedirectView("/board/suggest/list/personal");
    }

    @GetMapping("list/group")
    public String goToGroupList(Model model, @RequestParam(value="page", defaultValue="1") int page, @AuthenticationPrincipal UserDetail userDetail) {
        Page<SuggestDTO> suggestList = suggestService.getGroupSuggestList(page - 1);

        model.addAttribute("pageDTO",new PageDTO(suggestList));
        model.addAttribute("suggestDTOS", suggestList.getContent());
        model.addAttribute("userDetail", userDetail);
        return "/board/suggest/list";
    }

    @GetMapping("list/personal")
    public String goToPersonalList(Model model, @RequestParam(value="page", defaultValue="1") int page, @AuthenticationPrincipal UserDetail userDetail) {
        Page<SuggestDTO> suggestList = suggestService.getPersonalSuggestList(page - 1);
        log.info("" + suggestList.getTotalPages());
        log.info("" + suggestList.getTotalElements());
        model.addAttribute("pageDTO",new PageDTO(suggestList));
        model.addAttribute("suggestDTOS", suggestList.getContent());
        model.addAttribute("userDetail", userDetail);
        return "/board/suggest/list";
    }

    @GetMapping("detail/{boardId}")
    public String goToDetail(Model model, @PathVariable("boardId") Long boardId, @AuthenticationPrincipal UserDetail userDetail) {
        SuggestDTO suggestDTO = suggestService.getSuggest(boardId);

        model.addAttribute("suggestDTO", suggestDTO);
        model.addAttribute("userDetail", userDetail);

        return "/board/suggest/detail";
    }
}
