package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.inquire.AnswerDTO;
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.entity.inquire.Answer;
import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.repository.inquire.AnswerRepository;
import com.app.projectjar.repository.inquire.InquireRepository;
import org.springframework.stereotype.Service;

//@Service
//public class AnswerServiceImpl implements AnswerService {

//    private final AnswerRepository answerRepository;
//    private final InquireRepository inquireRepository;

//    public AnswerServiceImpl(AnswerRepository answerRepository, InquireRepository inquireRepository) {
//        this.answerRepository = answerRepository;
//        this.inquireRepository = inquireRepository;
//    }
//
//    public InquireDTO getInquireWithAnswer(Long inquireId) {
//        InquireDTO inquireDTO = toInquireDTO(inquireRepository.findById(inquireId).orElse(null));
//        if (inquireDTO != null) {
//            AnswerDTO answerDTO = toAnswerDTO(answerRepository.findByInquireId(inquireId).orElse(null));
//            inquireDTO.setAnswerDTO(answerDTO);
//        }
//        return inquireDTO;
//    }

//    private InquireDTO toInquireDTO(Inquire inquire) {
//    }
//
//    private AnswerDTO toAnswerDTO(Answer answer) {
//    }
//}
