package com.app.projectjar.service.notice;

import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.entity.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {
//    전체 목록 페이징
    Page<Notice> getAllNoticesWithPaging(Pageable pageable);

    // 저장
    public void register(NoticeDTO noticeDTO);

    // 상세 보기
    public NoticeDTO getNotice(Long noticeId);


    default Notice toNoticeEntity(NoticeDTO noticeDTO) {
        return Notice.builder()
                .id(noticeDTO.getId())
                .noticeTitle(noticeDTO.getNoticeTitle())
                .noticeContent(noticeDTO.getNoticeContent())
                .build();
    }

    default NoticeDTO toNoticeDTO(Notice notice) {
        return NoticeDTO.builder()
                .id(notice.getId())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .build();
    }
}
