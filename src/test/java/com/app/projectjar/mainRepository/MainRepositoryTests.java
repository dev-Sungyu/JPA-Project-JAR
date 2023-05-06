package com.app.projectjar.mainRepository;

import com.app.projectjar.repository.groupChallenge.GroupChallengeRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.suggest.SuggestLikeRepository;
import com.app.projectjar.repository.suggest.SuggestReplyRepository;
import com.app.projectjar.repository.suggest.SuggestRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class MainRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GroupChallengeRepository groupChallengeRepository;

    @Autowired
    private SuggestRepository suggestRepository;

    @Autowired
    private SuggestLikeRepository suggestLikeRepository;

    @Autowired
    private SuggestReplyRepository suggestReplyRepository;

//    @Test
//    private void findAllGroupChallenge(){
//
//    }

}
