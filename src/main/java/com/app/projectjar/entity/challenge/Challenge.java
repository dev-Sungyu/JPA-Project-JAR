package com.app.projectjar.entity.challenge;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.challenge.ChallengeFile;
import com.app.projectjar.type.ChallengeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Challenge extends Board {

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private ChallengeType challengeStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "challenge")
    private List<ChallengeFile> challengeFiles;

    public Challenge(String boardTitle, String boardContent, ChallengeType challengeStatus) {
        super(boardTitle, boardContent);
        this.challengeStatus = challengeStatus;
    }
    public Challenge(String boardTitle, String boardContent, ChallengeType challengeStatus, List<ChallengeFile> challengeFiles) {
        super(boardTitle, boardContent);
        this.challengeStatus = challengeStatus;
        this.challengeFiles = challengeFiles;


    }
}
