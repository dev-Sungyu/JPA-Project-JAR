package com.app.projectjar.domain.like;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class LikeDTO {
    private Long likeId;
    private Long memberId;
    private Long boardId;

    @Builder
    public LikeDTO(Long likeId, Long memberId, Long boardId) {
        this.likeId = likeId;
        this.memberId = memberId;
        this.boardId = boardId;
    }
}
