package com.app.projectjar.domain.challenge;

import com.app.projectjar.domain.file.FileDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class ChallengeDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;

    private List<FileDTO> fileDTOS;

    @Builder
    public ChallengeDTO(Long id, String boardTitle, String boardContent, List<FileDTO> fileDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.fileDTOS = fileDTOS;
    }

}
