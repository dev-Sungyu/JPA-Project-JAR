package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquireService {
    //    전체 목록 페이징
    public Page<InquireDTO> getAllInquiresWithPaging(int page);

    // 저장
    public void register(InquireDTO InquireDTO);

    // 상세 보기
    public InquireDTO getInquire(Long inquireId);

    // 삭제
    public void deleteInquire(Long inquireId);

    // 업데이트
    public void updateInquire(Long inquireId, InquireDTO inquireDTO);

    // 마이 페이지 문의 사항 목록 조회
    public Page<InquireDTO> getInquireForMemberIdList(Pageable pageable, Long id);



    default Inquire toInquireEntity(InquireDTO inquireDTO) {
        return Inquire.builder()
                .id(inquireDTO.getId())
                .inquireTitle(inquireDTO.getInquireTitle())
                .inquireContent(inquireDTO.getInquireContent())
                .build();
    }

    default InquireDTO toInquireDTO(Inquire inquire) {
        return InquireDTO.builder()
                .id(inquire.getId())
                .inquireTitle(inquire.getInquireTitle())
                .inquireContent(inquire.getInquireContent())
                .answerType((inquire.getAnswerType()))
                .memberDTO(toMemberDTO(inquire.getMember()))
                .build();
    }

    default MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberStatus(member.getMemberStatus())
                .build();
    }



}
