package com.app.projectjar.domain.board;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class BoardSearchDTO {
    private Long challengeTitle;
    private Long groupChallengeTitle;
    private Long suggestTitle;

    @Builder
    public BoardSearchDTO(Long challengeTitle, Long groupChallengeTitle, Long suggestTitle) {
        this.challengeTitle = challengeTitle;
        this.groupChallengeTitle = groupChallengeTitle;
        this.suggestTitle = suggestTitle;
    }
}
