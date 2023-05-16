package com.app.projectjar.controller.board.diary;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.diary.DiaryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/board/diary/*")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;


    @GetMapping("list")
    public void goToList(Model model, @AuthenticationPrincipal UserDetail userDetail){
        model.addAttribute("userDetail", userDetail);
    }

    @GetMapping("list-content")
    @ResponseBody
    public Slice<DiaryDTO> getDiaryList(@RequestParam("sort") String sort, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page, 12);
        return diaryService.getOpenDiaryList(sort, pageRequest);
    }

    @GetMapping("detail/{diaryId}")
    public String goToDetail(Model model, @AuthenticationPrincipal UserDetail userDetail, @PathVariable("diaryId") Long diaryId) {

        DiaryDTO diaryDTO = diaryService.getDiary(diaryId);
        model.addAttribute("diaryDTO", diaryDTO);
        model.addAttribute("userDetail", userDetail);
        return "/board/diary/detail";
    }

    @GetMapping("modify/{diaryId}")
    public String goToModify(Model model, @PathVariable("diaryId") Long diaryId) {

        DiaryDTO diaryDTO = diaryService.getDiary(diaryId);
        model.addAttribute("diaryDTO", diaryDTO);
        return "/board/diary/modify";
    }

    @PostMapping("modify")
    public RedirectView modify(@RequestParam("boardId") Long boardId, @ModelAttribute("diaryDTO") DiaryDTO diaryDTO) {

        diaryDTO.setId(boardId);
        diaryService.modifyDiary(diaryDTO);
        if(diaryDTO.getDiaryStatus().equals("PRIVATE")){
            return new RedirectView("/board/diary/detail/" + boardId);
        }else {
            return new RedirectView("/board/diary/list/");
        }
    }
}
