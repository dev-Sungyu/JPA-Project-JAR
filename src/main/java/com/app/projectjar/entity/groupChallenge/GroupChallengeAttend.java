package com.app.projectjar.entity.groupChallenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.GroupChallengeAttendType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_GROUP_CHALLENGE_ATTEND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class GroupChallengeAttend extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'NONPARTICIPANT'")
    private GroupChallengeAttendType groupChallengeAttendStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_CHALLENGE_ID")
    private GroupChallenge groupChallenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}