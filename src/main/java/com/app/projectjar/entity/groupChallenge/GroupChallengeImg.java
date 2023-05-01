package com.app.projectjar.entity.groupChallenge;

import com.app.projectjar.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_GROUP_CHALLENGE_IMG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupChallengeImg extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String groupChallengeImgOriginalName;
    @NotNull
    @Column(unique = true, length = 30)
    private String groupChallengeImgUuid;
    @NotNull
    private String groupChallengeImgPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_CHALLENGE_ID")
    private GroupChallenge groupChallenge;
}
