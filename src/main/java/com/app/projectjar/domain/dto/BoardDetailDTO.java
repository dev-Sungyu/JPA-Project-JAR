package com.app.projectjar.domain.dto;

import com.app.projectjar.type.BoardType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter @ToString
public class BoardDetailDTO {

    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private BoardType boardType;
    private LocalDateTime registerDate;

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

    public void setFileDTOs(List<FileDTO> fileDTOs) {
        this.fileDTOs = fileDTOs;
    }
}
