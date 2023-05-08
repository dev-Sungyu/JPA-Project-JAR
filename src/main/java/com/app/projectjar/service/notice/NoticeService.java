package com.app.projectjar.service.notice;

import com.app.projectjar.entity.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {
    Page<Notice> getAllNoticesWithPaging(Pageable pageable);
}
