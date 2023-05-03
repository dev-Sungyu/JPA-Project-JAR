package com.app.projectjar.entity.challenge;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.type.ChallengeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends Board {

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private ChallengeType challengeStatus;

}
