package com.app.projectjar.domain.dto;

import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.type.BoardType;
import com.app.projectjar.type.ChallengeType;
import com.app.projectjar.type.GroupChallengeType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @ToString
public class BoardDetailDTO {

    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime registerDate;

    private LocalDate startDate;
    private LocalDate endDate;

    private BoardType boardType;

    private ChallengeType challengeStatus;

    private GroupChallengeType groupChallengeStatus;

    private List<FileDTO> fileDTOs;

    @QueryProjection
    public BoardDetailDTO(Long boardId, String boardTitle, String boardContent, BoardType boardType) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardType = boardType;
    }

    @QueryProjection
    public BoardDetailDTO(Long boardId, String boardTitle, String boardContent, LocalDateTime registerDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.registerDate = registerDate;
    }

    @QueryProjection
    public BoardDetailDTO(Long boardId, String boardTitle, String boardContent, ChallengeType challengeStatus, LocalDateTime registerDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.challengeStatus = challengeStatus;
        this.registerDate = registerDate;
    }

    @QueryProjection
    public BoardDetailDTO(Long boardId, String boardTitle, String boardContent, LocalDate startDate,LocalDate endDate, GroupChallengeType groupChallengeStatus) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupChallengeStatus = groupChallengeStatus;
    }

    public void setFileDTOs(List<FileDTO> fileDTOs) {
        this.fileDTOs = fileDTOs;
    }
}
