package com.app.projectjar.entity.challenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.audit.Title;
import com.app.projectjar.type.ChallengeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends Title {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private ChallengeType challengeStatus;
}
