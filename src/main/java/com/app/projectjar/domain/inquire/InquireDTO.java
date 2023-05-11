package com.app.projectjar.domain.inquire;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class InquireDTO {
    private Long id;
    private String inquireTitle;
    private String inquireContent;

    @Builder
    public InquireDTO(Long id, String inquireTitle, String inquireContent) {
        this.id = id;
        this.inquireTitle = inquireTitle;
        this.inquireContent = inquireContent;
    }
}
