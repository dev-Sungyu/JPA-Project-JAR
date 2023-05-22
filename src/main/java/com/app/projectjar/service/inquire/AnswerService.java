package com.app.projectjar.service.inquire;

import com.app.projectjar.entity.inquire.Answer;
import com.app.projectjar.repository.inquire.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {
        private final AnswerRepository answerRepository;

        public AnswerService(AnswerRepository answerRepository) {
            this.answerRepository = answerRepository;
        }

        public Answer getAnswerByAdminNameAndAnswerId(String adminName, Long answerId) {
            return answerRepository.findAnswerByAdminNameAndAnswerId(adminName, answerId);
        }
}
