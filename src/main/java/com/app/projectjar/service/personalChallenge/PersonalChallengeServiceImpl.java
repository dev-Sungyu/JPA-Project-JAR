package com.app.projectjar.service.personalChallenge;

import com.app.projectjar.domain.challenge.ChallengeDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.repository.challenge.ChallengeRepository;
import com.app.projectjar.repository.personalChallenge.PersonalChallengeRepository;
import com.app.projectjar.type.ChallengeType;
import com.app.projectjar.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("personalChallenge") @Primary
public class PersonalChallengeServiceImpl implements PersonalChallengeService {

    private final PersonalChallengeRepository personalChallengeRepository;

    private final ChallengeRepository challengeRepository;

    @Override
    public Page<PersonalChallengeDTO> getListByChallengeStatus(String challengeStatus, Pageable pageable) {
        Page<PersonalChallenge> personalChallenges = personalChallengeRepository.findAllByChallengeStatus(challengeStatus, pageable);

        List<PersonalChallengeDTO> personalChallengeDTOS = personalChallenges.getContent().stream()
                .map(this::toPersonalChallengeDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(personalChallengeDTOS, pageable, personalChallenges.getTotalElements());
    }

    @Override
    public Page<PersonalChallengeDTO> getAllChallengesWithPaging(int page) {
        Page<PersonalChallenge> personalChallenges = personalChallengeRepository.findAllWithPaging_QueryDSL(PageRequest.of(page, 10));
        List<PersonalChallengeDTO> personalChallengeDTOS = personalChallenges.getContent().stream()
                .map(this::toPersonalChallengeDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(personalChallengeDTOS, personalChallenges.getPageable(), personalChallenges.getTotalElements());
    }

    @Override
    public List<PersonalChallenge> getListToYesterday(LocalDate yesterday) {
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

    @Override
    public List<Challenge> getChallengeList() {
        return challengeRepository.findAll_QueryDsl();
    }

    @Override
    public void insertChallenge(Challenge challenge) {
        PersonalChallenge personalChallenge = PersonalChallenge.builder().challenge(challenge).build();
        personalChallengeRepository.save(personalChallenge);
    }

}
