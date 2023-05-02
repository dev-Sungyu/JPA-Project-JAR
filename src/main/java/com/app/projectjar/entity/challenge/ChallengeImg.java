package com.app.projectjar.entity.challenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.type.Image;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_CHALLENGE_IMG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeImg extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Embedded
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

}
