package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.DiaryLike;
import com.app.projectjar.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiaryQueryLikeDsl {

    public Member findMemberByDiaryLike(Long diaryId, Long memberId);

    public Long getDiaryLikeCount(Long diaryId);

    public void deleteByMemberIdAndDiaryId(Long diaryId, Long memberId);

    public Page<DiaryLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long memberId);
}
