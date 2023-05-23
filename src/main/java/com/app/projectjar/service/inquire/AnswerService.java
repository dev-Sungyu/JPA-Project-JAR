package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.inquire.AnswerDTO;
import com.app.projectjar.entity.inquire.Answer;

public interface AnswerService {

    // 상세 보기
    public AnswerDTO getAnswer(Long answerId);

    default AnswerDTO toAnswerDTO(Answer answer) {
        return AnswerDTO.builder()
                .id(answer.getId())
                .answerContent(answer.getAnswerContent())
                .registerDate(answer.getCreatedDate())
                .build();
    }

}
