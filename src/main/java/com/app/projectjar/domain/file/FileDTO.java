package com.app.projectjar.domain.file;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.type.FileType;
import com.querydsl.core.annotations.QueryProjection;
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
    private FileType fileType;

    private Suggest suggest;

    @Builder
    public FileDTO(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    @Builder
    public FileDTO(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType,  Suggest suggest) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileType = fileType;
        this.suggest = suggest;
    }

    @QueryProjection
    public FileDTO(Long id, String fileOriginalName, String fileUuid, String filePath) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
    }
}
