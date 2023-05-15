package com.app.projectjar.entity.personalChallenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.challenge.Challenge;
import lombok.*;

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

    @Column(columnDefinition = "integer default 0")
    private Integer challengeReplyCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

}
