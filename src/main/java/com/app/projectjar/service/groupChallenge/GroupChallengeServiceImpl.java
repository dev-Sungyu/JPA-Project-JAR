package com.app.projectjar.service.groupChallenge;

import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.repository.groupChallenge.GroupChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("groupChallenge") @Primary
public class GroupChallengeServiceImpl implements GroupChallengeService {
    private final GroupChallengeRepository groupChallengeRepository;

    @Override
    public Page<GroupChallengeDTO> getGroupChallengeList(Pageable pageable) {
        Page<GroupChallenge> groupChallenges = groupChallengeRepository.findAllGroupChallengeWithPaging_QueryDsl(pageable);
        List<GroupChallengeDTO> groupChallengeDTOS = groupChallenges.stream().map(this::toGroupChallengeDTO).collect(Collectors.toList());

        return new PageImpl<>(groupChallengeDTOS, groupChallenges.getPageable() , groupChallenges.getTotalElements());
    }

    @Override
    public GroupChallengeDTO getGroupChallenge(Long groupChallengeId) {
        GroupChallengeDTO groupChallengeDTO = groupChallengeRepository.findByGroupChallengeId_QueryDsl(groupChallengeId).map(this::toGroupChallengeDTO).get();
        return groupChallengeDTO;
    }

    @Override
    public GroupChallenge getCurrentSequence() {
        return groupChallengeRepository.getCurrentSequence_QueryDsl();
    }
}
