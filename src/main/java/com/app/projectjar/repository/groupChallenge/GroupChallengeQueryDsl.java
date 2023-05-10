package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.entity.board.BoardSearch;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GroupChallengeQueryDsl {

    // 목록
    public Page<GroupChallenge> findAllGroupChallengeWithPaging_QueryDsl(Pageable pageable);
    // 상세 보기
    public Optional<GroupChallenge> findByGroupChallengeId_QueryDsl(Long groupChallengeId);

    // 목록(페이징 처리 없는 버전)
    public List<GroupChallenge> findAllGroupChallenge_QueryDsl();

    //    검색
    public Page<GroupChallenge> findAllWithSearch(BoardSearch boardSearch, Pageable pageable);

}
