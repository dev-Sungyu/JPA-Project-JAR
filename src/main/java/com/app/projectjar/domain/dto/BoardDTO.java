package com.app.projectjar.domain.dto;

import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.type.BoardType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter @ToString
public class BoardDTO {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private BoardType boardType;

    private List<FileDTO> files;

    @QueryProjection
    public BoardDTO(Long boardId, String boardTitle, String boardContent, BoardType boardType) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardType = boardType;
    }

    @QueryProjection
    public BoardDTO(Long boardId, String boardTitle, String boardContent, BoardType boardType, List<FileDTO> files) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardType = boardType;
        this.files = files;
    }

    public void setFiles(List<FileDTO> files) {
        this.files = files;
    }
}
