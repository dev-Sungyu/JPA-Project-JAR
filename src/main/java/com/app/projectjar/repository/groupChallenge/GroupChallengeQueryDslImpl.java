package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.domain.dto.*;
import com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile;
import com.app.projectjar.entity.groupChallenge.QGroupChallenge;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile.groupChallengeFile;
import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallenge.groupChallenge;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;

@RequiredArgsConstructor
public class GroupChallengeQueryDslImpl implements GroupChallengeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<BoardDTO> findAllGroupChallengeWithPaging(Pageable pageable) {
        List<BoardDTO> foundGroupChallenge = query.select(
                new QBoardDTO(
                        groupChallenge.id,
                        groupChallenge.boardTitle,
                        groupChallenge.boardContent,
                        groupChallenge.startDate,
                        groupChallenge.endDate,
                        groupChallenge.groupChallengeStatus
                ))
                .from(groupChallenge)
                .orderBy(groupChallenge.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        foundGroupChallenge.stream().forEach(
                boardDTO -> {
                    FileDTO fileDTO = query.select(new QFileDTO(
                            groupChallengeFile.id,
                            groupChallengeFile.fileOriginalName,
                            groupChallengeFile.fileUuid,
                            groupChallengeFile.filePath
                    )).from(groupChallengeFile)
                            .where(groupChallengeFile.groupChallenge.id.eq(boardDTO.getBoardId()))
                            .limit(1)
                            .fetchOne();
                    boardDTO.setFileDTO(fileDTO);
                }
        );

        Long count = query.select(groupChallenge.count())
                .from(groupChallenge)
                .fetchOne();

        return new PageImpl<>(foundGroupChallenge, pageable, count);
    }

    @Override
    public Optional<BoardDetailDTO> findByGroupChallengeId(Long groupChallengeId) {
        BoardDetailDTO boardDetailDTO = query.select(new QBoardDetailDTO(
                groupChallenge.id,
                groupChallenge.boardTitle,
                groupChallenge.boardContent,
                groupChallenge.startDate,
                groupChallenge.endDate,
                groupChallenge.groupChallengeStatus
        )).from(groupChallenge)
                .where(groupChallenge.id.eq(groupChallengeId))
                .fetchOne();

        boardDetailDTO.setFileDTOs(
                query.select(new QFileDTO(
                        groupChallengeFile.id,
                        groupChallengeFile.fileOriginalName,
                        groupChallengeFile.fileUuid,
                        groupChallengeFile.filePath
                )).from(groupChallengeFile)
                        .where(groupChallengeFile.groupChallenge.id.eq(groupChallengeId))
                        .fetch()
        );

        return Optional.ofNullable(boardDetailDTO);
    }
}
