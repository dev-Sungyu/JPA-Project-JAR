package com.app.projectjar.entity.suggest;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BoardType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true, exclude = "member")
@Table(name = "TBL_SUGGEST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Suggest extends Board {

    @NotNull
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "suggest")
    private List<SuggestFile> suggestFiles = new ArrayList<>();

    public Suggest(String boardTitle, String boardContent, BoardType boardType, Member member) {
        super(boardTitle, boardContent);
        this.boardType = boardType;
        this.member = member;
    }

    public Suggest(String boardTitle, String boardContent, BoardType boardType, Member member, List<SuggestFile> suggestFiles) {
        super(boardTitle, boardContent);
        this.boardType = boardType;
        this.member = member;
        this.suggestFiles = suggestFiles;
    }

    public Suggest(Long id, String boardTitle, String boardContent, BoardType boardType, Member member) {
        super(id, boardTitle, boardContent);
        this.boardType = boardType;
        this.member = member;
    }

    @Builder
    public Suggest(Long id, String boardTitle, String boardContent, BoardType boardType, Member member, List<SuggestFile> suggestFiles) {
        super(id, boardTitle, boardContent);
        this.boardType = boardType;
        this.member = member;
        this.suggestFiles = suggestFiles;
    }
}
