package com.app.projectjar.repository.challenge;

import com.app.projectjar.domain.dto.BoardDTO;
import com.app.projectjar.domain.dto.BoardDetailDTO;

import java.util.List;
import java.util.Optional;

public interface ChallengeQueryDsl {

    // 챌린지 type이 WAIT인 정보
    public List<BoardDTO> findAllByChallengeTypeToWait();

    // 챌린지 type이 OPEN인 정보
    public List<BoardDTO> findAllByChallengeTypeToOpen();

    // 챌린지 type이 PRIVATE 정보
    public List<BoardDTO> findAllByChallengeTypeToPrivate();

    // 챌린지 상세 보기
    public Optional<BoardDetailDTO> findByChallengeId(Long challengeId);
}
