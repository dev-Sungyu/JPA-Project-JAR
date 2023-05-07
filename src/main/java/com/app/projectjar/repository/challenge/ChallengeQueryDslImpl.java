package com.app.projectjar.repository.challenge;


import com.app.projectjar.domain.dto.*;
import com.app.projectjar.entity.board.QBoard;
import com.app.projectjar.entity.challenge.QChallenge;
import com.app.projectjar.entity.file.challenge.QChallengeFile;
import com.app.projectjar.type.ChallengeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.challenge.QChallenge.challenge;
import static com.app.projectjar.entity.file.challenge.QChallengeFile.challengeFile;
import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;

@RequiredArgsConstructor
public class ChallengeQueryDslImpl implements ChallengeQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public List<BoardDTO> findAllByChallengeTypeToWait() {
        List<BoardDTO> boardDTOs = query.select(new QBoardDTO(
                challenge.id,
                challenge.boardTitle,
                challenge.boardContent,
                challenge.challengeStatus,
                challenge.updatedDate
        )).from(challenge)
                .where(challenge.challengeStatus.eq(ChallengeType.valueOf("WAIT")))
                .fetch();

        boardDTOs.stream().forEach(
                boardDTO ->{
                    FileDTO fileDTO = query.select(new QFileDTO(
                            challengeFile.id,
                            challengeFile.fileOriginalName,
                            challengeFile.fileUuid,
                            challengeFile.filePath
                    )).from(challengeFile)
                            .where(challengeFile.challenge.id.eq(boardDTO.getBoardId()))
                            .limit(1)
                            .fetchOne();
                    boardDTO.setFileDTO(fileDTO);
                }
        );

        return boardDTOs;
    }

    @Override
    public List<BoardDTO> findAllByChallengeTypeToOpen() {
        List<BoardDTO> boardDTOs = query.select(new QBoardDTO(
                challenge.id,
                challenge.boardTitle,
                challenge.boardContent,
                challenge.challengeStatus,
                challenge.updatedDate
        )).from(challenge)
                .where(challenge.challengeStatus.eq(ChallengeType.valueOf("OPEN")))
                .fetch();

        boardDTOs.stream().forEach(
                boardDTO -> {
                    FileDTO fileDTO = query.select(new QFileDTO(
                            challengeFile.id,
                            challengeFile.fileOriginalName,
                            challengeFile.fileUuid,
                            challengeFile.filePath
                    )).from(challengeFile)
                            .where(challengeFile.challenge.id.eq(boardDTO.getBoardId()))
                            .limit(1)
                            .fetchOne();
                    boardDTO.setFileDTO(fileDTO);
                }
        );
        return boardDTOs;
    }

    @Override
    public List<BoardDTO> findAllByChallengeTypeToPrivate() {

        List<BoardDTO> boardDTOs = query.select(new QBoardDTO(
                challenge.id,
                challenge.boardTitle,
                challenge.boardContent,
                challenge.challengeStatus,
                challenge.updatedDate
        )).from(challenge)
                .where(challenge.challengeStatus.eq(ChallengeType.valueOf("PRIVATE")))
                .fetch();

        boardDTOs.stream().forEach(
                boardDTO -> {
                    FileDTO fileDTO = query.select(new QFileDTO(
                            challengeFile.id,
                            challengeFile.fileOriginalName,
                            challengeFile.fileUuid,
                            challengeFile.filePath
                    )).from(challengeFile)
                            .where(challengeFile.challenge.id.eq(boardDTO.getBoardId()))
                            .limit(1)
                            .fetchOne();
                    boardDTO.setFileDTO(fileDTO);
                }
        );
        return boardDTOs;
    }

    @Override
    public Optional<BoardDetailDTO> findByChallengeId(Long challengeId) {
        BoardDetailDTO boardDetailDTO = query.select(new QBoardDetailDTO(
                challenge.id,
                challenge.boardTitle,
                challenge.boardContent,
                challenge.challengeStatus,
                challenge.updatedDate
        )).from(challenge)
                .where(challenge.id.eq(challengeId))
                .fetchOne();

        boardDetailDTO.setFileDTOs(
                query.select(new QFileDTO(
                        suggestFile.id,
                        suggestFile.fileOriginalName,
                        suggestFile.fileUuid,
                        suggestFile.filePath
                )).from(suggestFile)
                        .where(suggestFile.suggest.id.eq(challengeId))
                        .fetch()
        );

        return Optional.ofNullable(boardDetailDTO);
    }
}
