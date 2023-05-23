package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.inquire.AnswerDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.inquire.Answer;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("answer") @Primary
@Slf4j
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    //    상세보기
    @Override
    public AnswerDTO getAnswer(Long answerId) {
        Optional<Answer> answer = .findByIdSuggest_QueryDsl(suggestId);
        return toSuggestDTO(suggest.get());
    }

}
