package com.app.projectjar.controller.board.suggest;

import com.app.projectjar.domain.dto.suggest.SuggestDTO;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board/suggest/*")
@RequiredArgsConstructor
public class SuggestController {
    private final SuggestService suggestService;

    @GetMapping("list")
    public List<SuggestDTO> list() {
        return suggestService.getSuggestList(PageRequest.of(1,10));
    }

}
