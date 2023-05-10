package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.SuggestLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SuggestLikeQueryDsl {

    public Member findMemberBySuggestLike(Long suggestId, Long memberId);

    public Long getSuggestLikeCount(Long suggestId);

    public void deleteByMemberIdAndSuggestId(Long suggestId, Long memberId);

}
