package com.app.projectjar.domain.inquire;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.type.AnswerType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@NoArgsConstructor
public class InquireDTO {
    private Long id;
    private String inquireTitle;
    private String inquireContent;
    private AnswerType answerType;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private MemberDTO memberDTO;

    @Builder
    public InquireDTO(Long id, String inquireTitle, String inquireContent, MemberDTO memberDTO, AnswerType answerType, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.inquireTitle = inquireTitle;
        this.inquireContent = inquireContent;
        this.memberDTO = memberDTO;
        this.createdDate = createDate;
        this.updatedDate = updateDate;

        this.answerType = answerType;

    }
}
