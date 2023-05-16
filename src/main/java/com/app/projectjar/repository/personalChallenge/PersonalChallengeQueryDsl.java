package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface PersonalChallengeQueryDsl {

    public List<PersonalChallenge> findAllByChallengeStatusToOpen();
}
