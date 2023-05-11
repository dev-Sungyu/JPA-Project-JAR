package com.app.projectjar.service.notice;

import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.entity.notice.Notice;
import com.app.projectjar.repository.notice.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("notice") @Primary
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Page<NoticeDTO> getAllNoticesWithPaging(int page) {
        Page<Notice> notices = noticeRepository.findAllWithPaging_QueryDSL(PageRequest.of(page, 10));
        List<NoticeDTO> noticeDTOS = notices.getContent().stream()
                .map(this::toNoticeDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(noticeDTOS, notices.getPageable(), notices.getTotalElements());
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

    @Override
    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    @Override
    public void updateNotice(Long noticeId, NoticeDTO noticeDTO) {
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeId);
        if (optionalNotice.isPresent()) {
            Notice existingNotice = optionalNotice.get();
            existingNotice.setNoticeTitle(noticeDTO.getNoticeTitle());
            existingNotice.setNoticeContent(noticeDTO.getNoticeContent());
            noticeRepository.save(existingNotice);
        } else {
        }
    }

}
