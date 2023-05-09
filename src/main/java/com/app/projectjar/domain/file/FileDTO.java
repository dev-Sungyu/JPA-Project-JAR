package com.app.projectjar.domain.file;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;

    @Builder
    public FileDTO(Long id, String fileOriginalName, String fileUuid, String filePath) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
    }
}
