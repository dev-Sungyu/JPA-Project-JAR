package com.app.projectjar.entity.groupChallenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.File;
import com.app.projectjar.type.Document;
import com.app.projectjar.type.GroupChallengeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_GROUP_CHALLENGE")
public class GroupChallenge extends Board {
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private GroupChallengeType groupChallengeStatus;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

}
