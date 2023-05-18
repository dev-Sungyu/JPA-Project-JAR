package com.app.projectjar.repository.challenge;

import com.app.projectjar.entity.board.BoardSearch;
import com.app.projectjar.entity.challenge.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChallengeQueryDsl {

    public List<Challenge> findAll_QueryDsl();
}
