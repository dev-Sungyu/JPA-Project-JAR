package com.app.projectjar.domain.dto;

import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.type.BoardType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter @ToString
public class BoardDTO {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime registerDate;
    private BoardType boardType;

    private FileDTO fileDTO;

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

    public void setFileDTO(FileDTO fileDTO) {
        this.fileDTO = fileDTO;
    }
}