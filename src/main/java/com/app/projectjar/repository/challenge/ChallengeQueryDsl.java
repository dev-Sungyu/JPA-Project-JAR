package com.app.projectjar.repository.challenge;

import com.app.projectjar.entity.board.BoardSearch;
import com.app.projectjar.entity.challenge.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChallengeQueryDsl {

    // 챌린지 type이 WAIT인 정보
    public List<Challenge> findAllByChallengeTypeToWait();

    // 챌린지 type이 OPEN인 정보
    public List<Challenge> findAllByChallengeTypeToOpen();

    // 챌린지 type이 PRIVATE 정보
    public List<Challenge> findAllByChallengeTypeToPrivate();

    // 챌린지 상세 보기
    public Optional<Challenge> findByChallengeId(Long challengeId);

    // 목록
    public Page<Challenge> findAllChallengeWithPaging_QueryDsl(Pageable pageable);

    //    검색
    public Page<Challenge> findAllWithSearch(BoardSearch boardSearch, Pageable pageable);

}
