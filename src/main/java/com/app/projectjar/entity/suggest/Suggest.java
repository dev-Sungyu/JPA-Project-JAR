package com.app.projectjar.entity.suggest;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BoardType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true, exclude = "member")
@Table(name = "TBL_SUGGEST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Suggest extends Board {

    @ColumnDefault(value = "0")
    private Integer suggestLikeCount;

    @ColumnDefault(value= "0")
    private Integer suggestReplyCount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "suggest")
    private List<SuggestFile> suggestFiles = new ArrayList<>();

    @Builder
    public Suggest(Long id, String boardTitle, String boardContent, BoardType boardType, Member member, List<SuggestFile> suggestFiles) {
        super(id, boardTitle, boardContent);
        this.boardType = boardType;
        this.member = member;
        this.suggestFiles = suggestFiles;
    }

    public void setSuggestLikeCount(Integer suggestLikeCount) {
        this.suggestLikeCount = suggestLikeCount;
    }

    public void setSuggestReplyCount(Integer suggestReplyCount) {
        this.suggestReplyCount = suggestReplyCount;
    }
}
