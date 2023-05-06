package com.app.projectjar.domain.dto;

import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.type.BedgeType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
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
    private BedgeType bedgeType;

    @QueryProjection
    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, Long memberId, String memberNickname, String originalFileName, String fileUuid, String filePath, BedgeType bedgeType) {
        this.id = id;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.originalFileName = originalFileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.bedgeType = bedgeType;
    }


//    @QueryProjection
//    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, Long memberId, String memberNickname, BedgeType bedgeType) {
//        this.id = id;
//        this.replyContent = replyContent;
//        this.registerDate = registerDate;
//        this.memberId = memberId;
//        this.memberNickname = memberNickname;
//        this.bedgeType = bedgeType;
//    }
}
