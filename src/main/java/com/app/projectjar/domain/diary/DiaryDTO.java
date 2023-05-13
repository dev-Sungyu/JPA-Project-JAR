package com.app.projectjar.domain.diary;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.type.DiaryType;
import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Data
@Getter @ToString
@NoArgsConstructor
public class DiaryDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private DiaryType diaryStatus;
    private Integer likeCount;
    private Integer replyCount;
    private MemberDTO memberDTO;
    private List<FileDTO> fileDTOS;

    @Builder
    public DiaryDTO(Long id, String boardTitle, String boardContent, DiaryType diaryStatus, Integer likeCount, Integer replyCount, MemberDTO memberDTO, List<FileDTO> fileDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.diaryStatus = diaryStatus;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
        this.memberDTO = memberDTO;
        this.fileDTOS = fileDTOS;
    }
}
