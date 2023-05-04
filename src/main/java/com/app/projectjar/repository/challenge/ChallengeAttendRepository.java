package com.app.projectjar.repository.challenge;

import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.challenge.ChallengeAttend;
import com.app.projectjar.entity.challenge.ChallengeReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeAttendRepository extends JpaRepository<ChallengeAttend, Long>, ChallengeAttendQueryDSL {
}
