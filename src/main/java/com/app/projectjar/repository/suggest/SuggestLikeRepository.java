package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestLikeRepository extends JpaRepository<SuggestLike, Long>, SuggestLikeQueryDSL {
}
