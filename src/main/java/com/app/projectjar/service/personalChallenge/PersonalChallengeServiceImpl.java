package com.app.projectjar.service.personalChallenge;

import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.repository.personalChallenge.PersonalChallengeRepository;
import com.app.projectjar.type.ChallengeType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("personalChallenge") @Primary
public class PersonalChallengeServiceImpl implements PersonalChallengeService {

    private final PersonalChallengeRepository personalChallengeRepository;

    @Override
    public Page<PersonalChallengeDTO> getListByChallengeStatus(String challengeStatus, Pageable pageable) {
        Page<PersonalChallenge> personalChallenges = personalChallengeRepository.findAllByChallengeStatus(challengeStatus, pageable);

        List<PersonalChallengeDTO> personalChallengeDTOS = personalChallenges.getContent().stream()
                .map(this::toPersonalChallengeDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(personalChallengeDTOS, pageable, personalChallenges.getTotalElements());
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

    @Override
    public PersonalChallengeDTO getPersonalChallenge(Long personalChallengeId) {
        Optional<PersonalChallenge> personalChallenge = personalChallengeRepository.findByPersonalChallengeId(personalChallengeId);
        return toPersonalChallengeDTO(personalChallenge.get());
    }
}
