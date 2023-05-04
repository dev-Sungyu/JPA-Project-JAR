package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestReplyRepository extends JpaRepository<SuggestReply, Long>, SuggestReplyQueryDsl {
}
