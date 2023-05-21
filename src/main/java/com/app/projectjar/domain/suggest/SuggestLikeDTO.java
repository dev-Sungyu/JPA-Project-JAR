package com.app.projectjar.domain.suggest;

import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SuggestLikeDTO {

    private Long suggestLikeId;
    private SuggestDTO suggestDTO;
    private MemberDTO memberDTO;

    @Builder
    public SuggestLikeDTO(Long suggestLikeId, SuggestDTO suggestDTO, MemberDTO memberDTO) {
        this.suggestLikeId = suggestLikeId;
        this.suggestDTO = suggestDTO;
        this.memberDTO = memberDTO;
    }
}
