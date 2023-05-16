package com.app.projectjar.service.groupChallenge.attend;


import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import com.app.projectjar.repository.groupChallenge.GroupChallengeAttendRepository;
import com.app.projectjar.repository.groupChallenge.GroupChallengeRepository;
import com.app.projectjar.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Qualifier("groupChallengeAttend") @Primary
public class GroupChallengeAttendServiceImpl implements GroupChallengeAttendService {
    private final GroupChallengeAttendRepository groupChallengeAttendRepository;

    private final GroupChallengeRepository groupChallengeRepository;

    private final MemberRepository memberRepository;

    @Override @Transactional
    public void insertAttend(Long boardId, Long memberId) {
        memberRepository.findById(memberId).ifPresent(
                member ->
                        groupChallengeRepository.findById(boardId).ifPresent(
                                groupChallenge -> {
                                    GroupChallengeAttend groupChallengeAttend = GroupChallengeAttend.builder()
                                            .groupChallenge(groupChallenge)
                                            .member(member)
                                            .build();
                                    groupChallengeAttendRepository.save(groupChallengeAttend);
                                    groupChallenge.setGroupChallengeAttendCount(getAttendCount(boardId));
                                    groupChallengeRepository.save(groupChallenge);
                                }
                        )
        );
    }

    @Override @Transactional
    public void deleteAttend(Long boardId, Long memberId) {
        groupChallengeAttendRepository.deleteByGroupChallengeIdAndMemberId(boardId, memberId);
        
        groupChallengeRepository.findById(boardId).ifPresent(
                groupChallenge -> {
                    groupChallenge.setGroupChallengeAttendCount(getAttendCount(boardId));
                    groupChallengeRepository.save(groupChallenge);
                }
        );
    }

    @Override
    public Boolean attendCheck(Long boardId, Long memberId) {
        return groupChallengeAttendRepository.findByChallengeIdAndMemberId(boardId,memberId) == 0;
    }

    @Override
    public void updateAttendToAttendType(Long boardId, Long memberId) {

    }

    @Override
    public Integer getAttendCount(Long boardId) {
        return groupChallengeAttendRepository.getAttendCountByGroupChallengeId(boardId);
    }
}
