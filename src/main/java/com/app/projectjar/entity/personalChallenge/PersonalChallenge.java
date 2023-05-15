package com.app.projectjar.entity.personalChallenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.type.ChallengeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString
@Table(name = "TBL_PERSONAL_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalChallenge extends Period {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private ChallengeType challengeStatus;

    @Column(columnDefinition = "integer default 0")
    private Integer challengeReplyCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

}
