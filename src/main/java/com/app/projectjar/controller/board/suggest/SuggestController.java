package com.app.projectjar.controller.board.suggest;

import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public RedirectView write(@ModelAttribute("suggestDTO") SuggestDTO suggestDTO, HttpServletRequest session) {
//        Long memberId = (Long)session.getAttribute("id");
        Long memberId = 1L;
        suggestService.register(suggestDTO, memberId);
        return new RedirectView("/board/suggest/list");
    }

    @GetMapping("list/group")
    public String goToGroupList(Model model, @PageableDefault(size = 16) Pageable pageable) {
        Page<SuggestDTO> suggestList = suggestService.getGroupSuggestList(pageable);


        int currentPage = suggestList.getPageable().getPageNumber() + 1;
        int startPage = Math.max(currentPage - 4 , 1);
        int endPage =  Math.min(currentPage + 5, suggestList.getTotalPages());

        suggestList.getContent().stream().map(SuggestDTO::toString).forEach(log::info);
        model.addAttribute("suggestDTOS", suggestList.getContent());
        return "/board/suggest/list";
    }

    @GetMapping("list/personal")
    public String goToPersonalList(Model model, @PageableDefault(size = 16) Pageable pageable) {
        Page<SuggestDTO> suggestList = suggestService.getPersonalSuggestList(pageable);

        int currentPage = suggestList.getPageable().getPageNumber() + 1;
        int startPage = Math.max(currentPage - 4 , 1);
        int endPage =  Math.min(currentPage + 5, suggestList.getTotalPages());

        suggestList.getContent().stream().map(SuggestDTO::toString).forEach(log::info);
        model.addAttribute("suggestDTOS", suggestList.getContent());

        return "/board/suggest/list";
    }

    @GetMapping("detail")
    public SuggestDTO goToDetail() {
        return null;
    }
}
