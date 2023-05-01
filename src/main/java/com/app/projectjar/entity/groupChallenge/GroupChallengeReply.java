package com.app.projectjar.entity.groupChallenge;


import com.app.projectjar.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_GROUP_CHALLENGE_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupChallengeReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String groupChallengeReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_CHALLENGE_ID")
    private GroupChallenge groupChallenge;
}
