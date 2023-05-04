package com.app.projectjar.entity.suggest;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BoardType;
import com.app.projectjar.type.ChallengeClassificationType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_SUGGEST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Suggest extends Board {

    @NotNull
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "suggest")
    private List<SuggestFile> suggestFiles;

    public Suggest(String boardTitle, String boardContent) {
        super(boardTitle, boardContent);
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
