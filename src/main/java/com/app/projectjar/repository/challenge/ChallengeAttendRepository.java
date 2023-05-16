package com.app.projectjar.repository.challenge;

import com.app.projectjar.entity.personalChallenge.ChallengeAttend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeAttendRepository extends JpaRepository<ChallengeAttend, Long>, ChallengeAttendQueryDsl {
}
