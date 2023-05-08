package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.domain.dto.BoardDTO;
import com.app.projectjar.domain.dto.BoardDetailDTO;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GroupChallengeQueryDsl {

    // 목록
    public Page<GroupChallenge> findAllGroupChallengeWithPaging_QueryDsl(Pageable pageable);
    // 상세 보기
    public Optional<GroupChallenge> findByGroupChallengeId_QueryDsl(Long groupChallengeId);

}
