package com.app.projectjar.entity.suggest;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.File;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.Document;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_SUGGEST")
public class Suggest extends Board {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "suggest", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<SuggestReply> suggestReplies = new ArrayList<>();

}
