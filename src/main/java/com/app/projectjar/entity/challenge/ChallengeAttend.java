package com.app.projectjar.entity.challenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.ChallengeAttendType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_CHALLENGE_ATTEND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeAttend extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'NONPARTICIPANT'")
    private ChallengeAttendType challengeAttendStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;
}
