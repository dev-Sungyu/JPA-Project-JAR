package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface PersonalChallengeQueryDsl {

    public List<PersonalChallenge> findAllByChallengeStatus(String challengeStatus);

    // 어제 insert된 목록 가져오기
    public List<PersonalChallenge> findByCreateDateYesterday(LocalDateTime localDateTime);
}
