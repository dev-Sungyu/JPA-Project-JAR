package com.app.projectjar.service.personalChallenge;

import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.repository.personalChallenge.PersonalChallengeRepository;
import com.app.projectjar.type.ChallengeType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("personalChallenge") @Primary
public class PersonalChallengeServiceImpl implements PersonalChallengeService {

    private final PersonalChallengeRepository personalChallengeRepository;

    @Override
    public List<PersonalChallengeDTO> getListToOpen() {
        List<PersonalChallengeDTO> challengeDTOList = new ArrayList<>();

        personalChallengeRepository.findAllByChallengeStatus().stream().forEach(
                personalChallenge -> {
                    challengeDTOList.add(toPersonalChallengeDTO(personalChallenge));
                }
        );
        return challengeDTOList;
    }

    @Override
    public List<PersonalChallenge> getListToYesterday(LocalDateTime yesterday) {
        return personalChallengeRepository.findByCreateDateYesterday(yesterday);
    }

    @Override
    public void updateChallengeStatus(List<PersonalChallenge> challengeList) {
        challengeList.stream().forEach(
                personalChallenge -> {
                    personalChallenge.setChallengeStatus(ChallengeType.PRIVATE);
                    personalChallengeRepository.save(personalChallenge);
                }
        );
    }
}
