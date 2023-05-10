package com.app.projectjar.service.notice;

import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.entity.notice.Notice;
import com.app.projectjar.repository.notice.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("notice") @Primary
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Page<Notice> getAllNoticesWithPaging(Pageable pageable) {
        return noticeRepository.findAllWithPaging_QueryDSL(pageable);
    }

    @Override
    public void register(NoticeDTO noticeDTO) {
        noticeRepository.save(toNoticeEntity(noticeDTO));
    }

    @Override
    public NoticeDTO getNotice(Long noticeId) {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        return toNoticeDTO(notice.get());
    }

}
