package com.app.projectjar.domain.inquire;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.AnswerType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class InquireDTO {
    private Long id;
    private String inquireTitle;
    private String inquireContent;
    private AnswerType answerType;

    private MemberDTO memberDTO;

    @Builder
    public InquireDTO(Long id, String inquireTitle, String inquireContent, MemberDTO memberDTO, AnswerType answerType) {
        this.id = id;
        this.inquireTitle = inquireTitle;
        this.inquireContent = inquireContent;
        this.memberDTO = memberDTO;
        this.answerType = answerType;
    }
}
