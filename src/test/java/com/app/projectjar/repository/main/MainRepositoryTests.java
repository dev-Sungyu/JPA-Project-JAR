package com.app.projectjar.repository.main;

import com.app.projectjar.entity.board.BoardSearch;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.repository.challenge.ChallengeRepository;
import com.app.projectjar.repository.groupChallenge.GroupChallengeRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.suggest.SuggestLikeRepository;
import com.app.projectjar.repository.suggest.SuggestReplyRepository;
import com.app.projectjar.repository.suggest.SuggestRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class MainRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private GroupChallengeRepository groupChallengeRepository;

    @Autowired
    private SuggestRepository suggestRepository;

    @Autowired
    private SuggestLikeRepository suggestLikeRepository;

    @Autowired
    private SuggestReplyRepository suggestReplyRepository;

//    그룹 챌린지 리스트
    @Test
    public void findAll() {
        groupChallengeRepository.findAll().forEach(groupChallenge -> log.info(String.valueOf(groupChallenge)));
    }

    @Test
    public void testFindAllWithSearch() {
        // 검색 조건을 설정합니다.
        BoardSearch boardSearch = new BoardSearch();
        boardSearch.setBoardTitle("개인"); // 검색어 설정 (필요에 따라 다른 조건도 추가 가능)

        // 검색 결과를 가져옵니다.
        List<Challenge> challenges = challengeRepository.findAllWithSearch(boardSearch);

        // 검색 결과를 확인합니다.
        Assertions.assertNotNull(challenges);
}}
