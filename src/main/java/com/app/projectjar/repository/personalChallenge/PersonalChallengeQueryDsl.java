package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonalChallengeQueryDsl {

    public Page<PersonalChallenge> findAllByChallengeStatus(String challengeStatus, Pageable pageable);

    // 어제 insert된 목록 가져오기
    public List<PersonalChallenge> findByCreateDateYesterday(LocalDateTime localDateTime);
}
