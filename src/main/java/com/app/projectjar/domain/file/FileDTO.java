package com.app.projectjar.domain.file;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
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

    private Suggest suggest;

    @Builder
    public FileDTO(Long id, String fileOriginalName, String fileUuid, String filePath) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
    }

    @Builder
    public FileDTO(Long id, String fileOriginalName, String fileUuid, String filePath, Suggest suggest) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.suggest = suggest;
    }
}
