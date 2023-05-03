package com.app.projectjar.entity.diary;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.DiaryType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name ="TBL_DIARY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends Board {
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private DiaryType diaryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
