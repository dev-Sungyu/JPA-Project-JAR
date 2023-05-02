package com.app.projectjar.entity.challenge;

import com.app.projectjar.audit.Image;
import com.app.projectjar.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_CHALLENGE_IMG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeImg extends Image {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;
}
