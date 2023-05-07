package com.app.projectjar.domain.dto;

import com.app.projectjar.type.BadgeType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Getter @ToString
public class ReplyDTO {

    private Long id;
    private String replyContent;
    private LocalDateTime registerDate;

    private Long memberId;
    private String memberNickname;
    private String originalFileName;
    private String fileUuid;
    private String filePath;
    private BadgeType badgeType;

    @QueryProjection
    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, Long memberId, String memberNickname, String originalFileName, String fileUuid, String filePath, BadgeType badgeType) {
        this.id = id;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.originalFileName = originalFileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.badgeType = badgeType;
    }


//    @QueryProjection
//    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, Long memberId, String memberNickname, BadgeType badgeType) {
//        this.id = id;
//        this.replyContent = replyContent;
//        this.registerDate = registerDate;
//        this.memberId = memberId;
//        this.memberNickname = memberNickname;
//        this.badgeType = badgeType;
//    }
}
