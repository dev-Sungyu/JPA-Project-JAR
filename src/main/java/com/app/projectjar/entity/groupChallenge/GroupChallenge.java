package com.app.projectjar.entity.groupChallenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.type.GroupChallengeType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_GROUP_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupChallenge extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String groupChallengeTitle;
    @NotNull
    private String groupChallengeContent;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GroupChallengeType groupChallengeStatus;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

}
