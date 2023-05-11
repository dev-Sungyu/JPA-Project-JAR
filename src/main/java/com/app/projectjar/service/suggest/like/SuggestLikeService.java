package com.app.projectjar.service.suggest.like;

import com.app.projectjar.domain.like.LikeDTO;

public interface SuggestLikeService {

    // 좋아요 갯수
    public Integer getLikeCount(Long suggestId);
    // 좋아요 ++
    public void heartUp(LikeDTO likeDTO);
    // 좋아요 --
    public void heartDown(LikeDTO likeDTO);
    // 좋아요 한 게시물인지 검사
    public Boolean heartCheck(LikeDTO likeDTO);
}
