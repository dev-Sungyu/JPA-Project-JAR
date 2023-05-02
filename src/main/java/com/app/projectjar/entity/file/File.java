package com.app.projectjar.entity.file;

import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.type.BoardType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String imgOriginalName;
    private String imgUuid;
    private String imgPath;
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupChallenge groupChallenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn("")
    private Suggest suggest;

    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

}
