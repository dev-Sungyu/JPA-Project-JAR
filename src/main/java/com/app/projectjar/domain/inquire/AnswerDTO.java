package com.app.projectjar.domain.inquire;

import com.app.projectjar.type.AnswerType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class AnswerDTO {
    private Long id;
    private String answerContent;
    private LocalDateTime registerDate;

    @Builder
    public AnswerDTO(Long id, String answerContent, LocalDateTime registerDate) {
        this.id = id;
        this.answerContent = answerContent;
        this.registerDate = registerDate;
    }
}
