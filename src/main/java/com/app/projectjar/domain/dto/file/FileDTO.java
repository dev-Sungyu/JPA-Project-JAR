package com.app.projectjar.domain.dto.file;

import com.app.projectjar.domain.dto.suggest.SuggestDTO;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    private SuggestDTO suggestDTO;

    public SuggestFile suggestFileToEntity() {
        return new SuggestFile(
                this.id,
                this.fileOriginalName,
                this.fileUuid,
                this.filePath,
                this.suggestDTO.suggestToEntity()
        );
    }
}
