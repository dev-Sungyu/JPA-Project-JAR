package com.app.projectjar.entity.challenge;

import com.app.projectjar.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_CHALLENGE_REPLY")
public class ChallengeReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String challengeReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;
}
