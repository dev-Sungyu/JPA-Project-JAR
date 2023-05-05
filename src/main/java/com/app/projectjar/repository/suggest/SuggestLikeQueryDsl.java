package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.member.Member;

public interface SuggestLikeQueryDsl {

    public Member findMemberBySuggestLike(Long suggestId, Long memberId);

    public Long getSuggestLikeCount(Long suggestId);

    public void deleteByMemberIdAndSuggestId(Long suggestId, Long memberId);

}
