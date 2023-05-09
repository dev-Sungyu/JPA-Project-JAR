package com.app.projectjar.domain.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;

}
