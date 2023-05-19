package com.app.projectjar.domain.file;

import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
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
    private Diary diary;
    private GroupChallenge groupChallenge;
    private Challenge challenge;


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

    @Builder
    public FileDTO(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType,  GroupChallenge groupChallenge) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileType = fileType;
        this.groupChallenge = groupChallenge;
    }
    @Builder
    public FileDTO(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType, Challenge challenge) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileType = fileType;
        this.challenge = challenge;
    }

    @QueryProjection
    public FileDTO(Long id, String fileOriginalName, String fileUuid, String filePath) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
    }
}
