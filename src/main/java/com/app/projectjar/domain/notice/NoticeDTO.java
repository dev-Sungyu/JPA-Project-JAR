package com.app.projectjar.domain.notice;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
@NoArgsConstructor
public class NoticeDTO {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private LocalDate updateDate;

    @Builder
    public NoticeDTO(Long id, String noticeTitle, String noticeContent, LocalDate updateDate) {
        this.id = id;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.updateDate = updateDate;
    }
}
