package com.app.projectjar.service.file;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.entity.file.suggest.SuggestFile;

import java.util.List;

public interface SuggestFileService {

    public void writeList(List<FileDTO> fileDTOS);

    default SuggestFile toSuggestFileEntity(FileDTO fileDTO){
        return SuggestFile.builder()
                .id(fileDTO.getId())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .build();
    }
}
