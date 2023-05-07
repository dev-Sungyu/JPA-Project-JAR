package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.domain.dto.BoardDTO;
import com.app.projectjar.domain.dto.BoardDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GroupChallengeQueryDsl {

    // 목록
    public Page<BoardDTO> findAllGroupChallengeWithPaging(Pageable pageable);
    // 상세 보기
    public Optional<BoardDetailDTO> findByGroupChallengeId(Long groupChallengeId);

}
