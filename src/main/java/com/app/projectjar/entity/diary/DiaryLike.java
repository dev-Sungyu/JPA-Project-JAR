package com.app.projectjar.entity.diary;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_DIARY_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryLike extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
