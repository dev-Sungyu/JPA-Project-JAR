package com.app.projectjar.domain;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.type.BadgeType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@NoArgsConstructor
public class ReplyDTO {

    private Long id;
    private String replyContent;
    private LocalDateTime registerDate;

    private MemberDTO memberDTO;

    @Builder
    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, MemberDTO memberDTO) {
        this.id = id;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
    }
}
