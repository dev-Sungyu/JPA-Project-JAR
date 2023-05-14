package com.app.projectjar.controller.inquire;

import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.domain.page.PageDTO;
import com.app.projectjar.service.inquire.InquireService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("board/inquire/*")
@RequiredArgsConstructor
public class InquireController {
    private final InquireService inquireService;

    @GetMapping("detail")
    public void inquireDetail(){}

    @GetMapping("list")
    public String getAllInquires(Model model, @RequestParam(value="page", defaultValue="1") int page) {
        Page<InquireDTO> inquirePage = inquireService.getAllInquiresWithPaging(page - 1);
        List<String> inquiresTitle = inquirePage.stream().map(InquireDTO::getInquireTitle).collect(Collectors.toList());
        model.addAttribute("pageDTO",new PageDTO(inquirePage));
        model.addAttribute("inquireDTOS", inquirePage.getContent());
        return "board/inquire/list";
    }


    @GetMapping("write")
    public void inquireWrite(){}

//    답변
//    @GetMapping
}
