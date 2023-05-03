package com.app.projectjar.entity.suggest;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@Table(name = "TBL_SUGGEST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Suggest extends Board {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
