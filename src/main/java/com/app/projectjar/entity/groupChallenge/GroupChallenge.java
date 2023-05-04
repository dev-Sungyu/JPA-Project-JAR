package com.app.projectjar.entity.groupChallenge;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import com.app.projectjar.type.GroupChallengeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_GROUP_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class GroupChallenge extends Board {
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private GroupChallengeType groupChallengeStatus;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "groupChallenge")
    private List<GroupChallengeFile> groupChallengeFiles;
}
