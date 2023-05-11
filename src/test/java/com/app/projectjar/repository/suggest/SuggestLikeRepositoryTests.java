package com.app.projectjar.repository.suggest;

import com.app.projectjar.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class SuggestLikeRepositoryTests {

    @Autowired
    private SuggestLikeRepository suggestLikeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SuggestRepository suggestRepository;


//    @Test
//    public void saveTest() {
//        memberRepository.findById(7L).ifPresent(
//                member ->
//                        suggestRepository.findById(17L).ifPresent(
//                                suggest ->
//                                suggestLikeRepository.save(new SuggestLike(member, suggest))
//                        )
//        );
//    }

    @Test
    public void getSuggestLikeCountTest() {
        log.info(suggestLikeRepository.getSuggestLikeCount(40L) + "개");
    }

    @Test
    public void findMemberBySuggestLikeTest() {
        log.info(suggestLikeRepository.findMemberBySuggestLike(40L,1L).toString());
    }

    @Test
    public void deleteByMemberId() {
        Long member = suggestLikeRepository.findMemberBySuggestLike(40L , 1L);
        suggestLikeRepository.deleteByMemberIdAndSuggestId(40L, 1L);
        assertThat(member).isNotNull();
    }


//    @Test
//    public void findByLikeMemberIdWithPaging_QueryDsl(){
//        PageRequest pageRequest = PageRequest.of(0, 5);
//        suggestLikeRepository.findByLikeMemberIdWithPaging_QueryDsl(pageRequest, 1L).stream().map(SuggestLike::toString).forEach(log::info);
//        log.info("@@@@@@@@@");
//    }

}
