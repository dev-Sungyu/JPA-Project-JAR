package com.app.projectjar.service.groupChallenge.attend;


import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import com.app.projectjar.repository.groupChallenge.GroupChallengeAttendRepository;
import com.app.projectjar.repository.groupChallenge.GroupChallengeRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.ChallengeAttendType;
import com.app.projectjar.type.ChallengeType;
import com.app.projectjar.type.GroupChallengeAttendType;
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
                                    groupChallenge.setGroupChallengeAttendCount(getAttendCount(groupChallenge.getId()));
                                    groupChallengeRepository.save(groupChallenge);
                                }
                        )
        );
    }

    @Override @Transactional
    public void deleteAttend(Long boardId, Long memberId) {
        memberRepository.findById(memberId).ifPresent(
                member ->
                        groupChallengeRepository.findById(boardId).ifPresent(
                                groupChallenge -> {
                                    groupChallengeAttendRepository.deleteByGroupChallengeIdAndMemberId_QueryDsl(groupChallenge.getId(), member.getId());
                                    groupChallenge.setGroupChallengeAttendCount(getAttendCount(groupChallenge.getId()));
                                    groupChallengeRepository.save(groupChallenge);
                                }
                        )
        );
    }

    @Override
    public Boolean attendCheck(Long boardId, Long memberId) {
        return groupChallengeAttendRepository.findByChallengeIdAndMemberId_QueryDsl(boardId,memberId) == 0;
    }

    @Override
    public Boolean challengeSuccessCheck(Long boardId, Long memberId) {
        GroupChallengeAttend groupChallengeAttend = groupChallengeAttendRepository.findGroupChallengeAttendByGroupChallengeIdAndMemberId_QueryDsl(boardId, memberId);
        return groupChallengeAttend.getGroupChallengeAttendStatus().equals(GroupChallengeAttendType.PARTICIPATION);
    }

    @Override
    public void updateAttendToAttendType(Long boardId, Long memberId) {
        GroupChallengeAttend groupChallengeAttend = groupChallengeAttendRepository.findGroupChallengeAttendByGroupChallengeIdAndMemberId_QueryDsl(boardId, memberId);
        groupChallengeAttend.setGroupChallengeAttendStatus(GroupChallengeAttendType.PARTICIPATION);
        groupChallengeAttendRepository.save(groupChallengeAttend);
    }

    @Override
    public Integer getAttendCount(Long boardId) {
        return groupChallengeAttendRepository.getAttendCountByGroupChallengeId_QueryDsl(boardId);
    }
}
