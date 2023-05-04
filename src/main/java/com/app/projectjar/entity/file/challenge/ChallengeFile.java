package com.app.projectjar.entity.file.challenge;

import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.file.Files;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_CHALLENGE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;
}
