package com.app.projectjar.controller.board.suggest.like;

import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.service.suggest.SuggestService;
import com.app.projectjar.service.suggest.like.SuggestLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suggest/like/*")
public class SuggestLikeController {
    private final SuggestLikeService suggestLikeService;

    // 좋아요 카운트 1 증가
    @PostMapping("likeUp")
    public void likeInsert(@RequestBody LikeDTO likeDTO) {
        suggestLikeService.heartUp(likeDTO);
    }

    // 좋아요 카운트 1 감소
    @DeleteMapping("likeDown")
    public void likeDown(@RequestBody FreeLikeVO freeLikeVO){
        likeService.likeDown(freeLikeVO);
    }

}
