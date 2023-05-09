package com.app.projectjar.controller.board.suggest;

import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("list")
    public void goToList() {
    }

    @GetMapping("detail")
    public SuggestDTO goToDetail() {
        return null;
    }
}
