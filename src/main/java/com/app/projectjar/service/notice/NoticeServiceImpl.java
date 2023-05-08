package com.app.projectjar.service.notice;

import com.app.projectjar.entity.notice.Notice;
import com.app.projectjar.repository.notice.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Page<Notice> getAllNoticesWithPaging(Pageable pageable) {
        return noticeRepository.findAllWithPaging_QueryDSL(pageable);
    }
}
