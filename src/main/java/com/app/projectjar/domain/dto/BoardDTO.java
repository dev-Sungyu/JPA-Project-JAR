package com.app.projectjar.domain.dto;

import com.app.projectjar.entity.file.Files;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.type.BoardType;
import com.app.projectjar.type.ChallengeType;
import com.app.projectjar.type.GroupChallengeType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@ToString
public class BoardDTO {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime registerDate;

    private LocalDate startDate;
    private LocalDate endDate;

    private ChallengeType challengeType;

    private GroupChallengeType groupChallengeStatus;

    private BoardType boardType;

    private FileDTO fileDTO;

    private Long fileId;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;

    @QueryProjection
    public BoardDTO(Long boardId, String boardTitle, String boardContent, BoardType boardType) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardType = boardType;
    }

    @QueryProjection
    public BoardDTO(Long boardId, String boardTitle, String boardContent, LocalDateTime registerDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.registerDate = registerDate;
    }

    @QueryProjection
    public BoardDTO(Long boardId, String boardTitle, String boardContent, ChallengeType challengeType, LocalDateTime registerDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.challengeType = challengeType;
        this.registerDate = registerDate;
    }


    public BoardDTO(){;}

    public void setFileDTO(FileDTO fileDTO) {
        this.fileDTO = fileDTO;
    }
}
