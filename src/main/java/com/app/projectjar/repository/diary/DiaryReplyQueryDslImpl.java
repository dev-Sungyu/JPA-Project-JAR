package com.app.projectjar.repository.diary;


import com.app.projectjar.domain.dto.ReplyDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class DiaryReplyQueryDslImpl implements DiaryReplyQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ReplyDTO> findAllByDiaryWithPaging(Long suggestId, Pageable pageable) {
        return null;
    }

    @Override
    public Long getReplyCount(Long suggestId) {
        return null;
    }
}
