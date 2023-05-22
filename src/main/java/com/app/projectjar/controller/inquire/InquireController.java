package com.app.projectjar.controller.inquire;

import com.app.projectjar.domain.challenge.ChallengeDTO;
import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.page.PageDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.inquire.InquireService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("board/inquire/*")
@RequiredArgsConstructor
@Slf4j
public class InquireController {
    private final InquireService inquireService;

    @GetMapping("detail/{inquireId}")
    public String inquireDetail(Model model, @PathVariable("inquireId") Long inquireId) {
        InquireDTO inquireDTO = inquireService.getInquire(inquireId);
        model.addAttribute("inquireDTO", inquireDTO);

        log.info("들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가들어가");
        return "board/inquire/detail";


    }


    @GetMapping("list")
    public String getAllInquires(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<InquireDTO> inquirePage = inquireService.getAllInquiresWithPaging(page - 1);
        List<String> inquiresTitle = inquirePage.stream().map(InquireDTO::getInquireTitle).collect(Collectors.toList());
        model.addAttribute("pageDTO", new PageDTO(inquirePage));
        model.addAttribute("inquireDTOS", inquirePage.getContent());

        log.info("노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답노답");
        return "board/inquire/list";
    }

    @GetMapping("write")
    public void goToWriteForm(InquireDTO inquireDTO) {
    }

    @PostMapping("write")
    public RedirectView write(@ModelAttribute("inquireDTO") InquireDTO inquireDTO, @AuthenticationPrincipal UserDetail userDetail) {

        Long memberId = userDetail.getId();
        inquireService.register(inquireDTO, memberId);
        return new RedirectView("/board/inquire/list");
    }

}



//    답변
//    @GetMapping
//}
