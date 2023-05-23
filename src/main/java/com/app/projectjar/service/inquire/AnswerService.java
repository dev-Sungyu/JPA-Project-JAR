package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.inquire.AnswerDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;

public interface AnswerService {

    // 상세 보기
    public AnswerDTO getAnswer(Long answerId);
}
