package com.app.projectjar.repository.groupChallenge;

import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChallengeRepository extends JpaRepository<GroupChallenge, Long>, GroupChallengeQueryDSL{
}
