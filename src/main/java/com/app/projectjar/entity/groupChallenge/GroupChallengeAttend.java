package com.app.projectjar.entity.groupChallenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.GroupChallengeAttendType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_GROUP_CHALLENGE_ATTEND")
public class GroupChallengeAttend extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GroupChallengeAttendType groupChallengeAttendStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_CHALLENGE_ID")
    @ColumnDefault("'NONPARTICIPANT'")
    private GroupChallenge groupChallenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
