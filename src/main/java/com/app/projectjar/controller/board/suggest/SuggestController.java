package com.app.projectjar.controller.board.suggest;

import com.app.projectjar.domain.page.PageDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        Long memberId = userDetail.getId();
        suggestService.register(suggestDTO, memberId);
        return new RedirectView("/board/suggest/list");
    }

    @GetMapping("list")
    public void goToList(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDetail",userDetail);
    }

    @GetMapping("list-content")
    @ResponseBody
    public Page<SuggestDTO> getList(@RequestParam("boardType") String boardType, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page, 12);
        return suggestService.getSuggestListByBoardType(boardType, pageRequest);
    }

    @GetMapping("detail/{boardId}")
    public String goToDetail(Model model, @PathVariable("boardId") Long boardId, @AuthenticationPrincipal UserDetail userDetail) {
        SuggestDTO suggestDTO = suggestService.getSuggest(boardId);

        model.addAttribute("suggestDTO", suggestDTO);
        model.addAttribute("userDetail", userDetail);
        return "/board/suggest/detail";
    }

    @GetMapping("modify/{boardId}")
    public String goToModify(Model model, @PathVariable("boardId") Long boardId) {
        SuggestDTO suggestDTO = suggestService.getSuggest(boardId);

        model.addAttribute("suggestDTO", suggestDTO);
        return "/board/suggest/modify";
    }

    @PostMapping("modify")
    public RedirectView modify(@RequestParam("boardId") Long boardId, @ModelAttribute("suggestDTO") SuggestDTO suggestDTO) {

        suggestDTO.getFileDTOS().stream().forEach(fileDTO -> log.info(fileDTO.toString()));
        suggestDTO.setId(boardId);
        suggestService.update(suggestDTO);
        return new RedirectView("/board/suggest/detail/" + boardId);
    }

    @PostMapping("delete/{boardId}")
    public RedirectView delete(@PathVariable("boardId") Long boardId){
        suggestService.delete(boardId);
        return new RedirectView("/board/suggest/list/personal");
    }

}
