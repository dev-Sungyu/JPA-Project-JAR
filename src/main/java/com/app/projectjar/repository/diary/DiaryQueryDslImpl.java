package com.app.projectjar.repository.diary;


import com.app.projectjar.domain.dto.*;
import com.app.projectjar.entity.diary.QDiary;
import com.app.projectjar.entity.file.diary.QDiaryFile;
import com.app.projectjar.type.DiaryType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.diary.QDiary.diary;
import static com.app.projectjar.entity.file.diary.QDiaryFile.diaryFile;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;

@RequiredArgsConstructor
public class DiaryQueryDslImpl implements DiaryQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public Optional<BoardDetailDTO> findByDiaryId(Long diaryId) {
        BoardDetailDTO boardDetailDTO = query.select(new QBoardDetailDTO(
                diary.id,
                diary.boardTitle,
                diary.boardContent,
                diary.createdDate
        )).from(diary)
                .where(diary.id.eq(diaryId))
                .fetchOne();

        boardDetailDTO.setFileDTOs(
                query.select(new QFileDTO(
                        diaryFile.id,
                        diaryFile.fileOriginalName,
                        diaryFile.fileUuid,
                        diaryFile.filePath
                )).from(diaryFile)
                        .where(diaryFile.diary.id.eq(diaryId))
                        .fetch()
        );

        return Optional.ofNullable(boardDetailDTO);
    }

    @Override
    public Page<BoardDTO> findAllDiary(Pageable pageable) {
        List<BoardDTO> foundDiaries = query.select(new QBoardDTO(
                diary.id,
                diary.boardTitle,
                diary.boardContent,
                diary.createdDate
        )).from(diary)
                .where(diary.diaryStatus.eq(DiaryType.valueOf("OPEN")))
                .orderBy(diary.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        foundDiaries.stream().forEach(
                boardDTO -> {
                    FileDTO fileDTO = query.select(new QFileDTO(
                            diaryFile.id,
                            diaryFile.fileOriginalName,
                            diaryFile.fileUuid,
                            diaryFile.filePath
                    )).from(diaryFile)
                            .where(diaryFile.diary.id.eq(boardDTO.getBoardId()))
                            .limit(1)
                            .fetchOne();
                    boardDTO.setFileDTO(fileDTO);
                }
        );

        Long count = query.select(diary.count())
                .from(diary)
                .where(diary.diaryStatus.eq(DiaryType.valueOf("OPEN")))
                .fetchOne();

        return new PageImpl<>(foundDiaries, pageable, count);
    }
}
