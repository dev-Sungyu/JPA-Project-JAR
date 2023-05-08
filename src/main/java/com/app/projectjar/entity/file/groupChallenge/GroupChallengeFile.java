package com.app.projectjar.entity.file.groupChallenge;

import com.app.projectjar.entity.file.Files;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_GROUP_CHALLENGE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupChallengeFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_CHALLENGE_ID")
    private GroupChallenge groupChallenge;

    public GroupChallengeFile(String fileOriginalName, String fileUuid, String filePath, GroupChallenge groupChallenge) {
        super(fileOriginalName, fileUuid, filePath);
        this.groupChallenge = groupChallenge;
    }
}
