package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.entity.inquire.Inquire;
import org.springframework.data.domain.Page;

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
                .build();
    }


}
