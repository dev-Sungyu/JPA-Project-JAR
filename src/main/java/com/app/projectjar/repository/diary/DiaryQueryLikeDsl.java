package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.member.Member;

public interface DiaryQueryLikeDsl {

    public Member findMemberByDiaryLike(Long diaryId, Long memberId);

    public Long getDiaryLikeCount(Long diaryId);

    public void deleteByMemberIdAndDiaryId(Long diaryId, Long memberId);
}
