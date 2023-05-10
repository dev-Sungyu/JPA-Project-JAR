package com.app.projectjar.domain.notice;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class NoticeDTO {
    private Long id;
    private String noticeTitle;
    private String noticeContent;

    @Builder
    public NoticeDTO(Long id, String noticeTitle, String noticeContent) {
        this.id = id;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }
}
