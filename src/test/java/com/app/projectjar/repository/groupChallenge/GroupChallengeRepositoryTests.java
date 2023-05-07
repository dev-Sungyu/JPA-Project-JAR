package com.app.projectjar.repository.groupChallenge;

import com.app.projectjar.domain.dto.BoardDTO;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.type.GroupChallengeType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class GroupChallengeRepositoryTests {

    @Autowired
    private GroupChallengeRepository groupChallengeRepository;

    @Test
    public void saveTest() {
        for (int i = 0; i < 10; i++) {
            groupChallengeRepository.save(new GroupChallenge(
                    "그룹 챌린지 제목" + (i + 1),
                    "그룹 챌린지 내용" + (i + 1),
                    GroupChallengeType.PRIVATE,
                    LocalDate.of(2023,05,8 + i),
                    LocalDate.of(2023,05,10 + i)));
        }
    }

    @Test
    public void findAllGroupChallengeWithPagingTest() {
        PageRequest pageRequest = PageRequest.of(0,10);
        groupChallengeRepository.findAllGroupChallengeWithPaging(pageRequest).stream().map(BoardDTO::toString).forEach(log::info);
    }

}
