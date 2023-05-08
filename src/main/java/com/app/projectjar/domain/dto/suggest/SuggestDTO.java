package com.app.projectjar.domain.dto.suggest;

import com.app.projectjar.domain.dto.file.FileDTO;
import com.app.projectjar.domain.dto.member.MemberDTO;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.type.BoardType;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SuggestDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private BoardType boardType;
    private MemberDTO memberDTO;
    private List<FileDTO> fileDTOS;


    public Suggest suggestToEntity(){
        return new Suggest(
                this.id,
                this.boardTitle,
                this.boardContent,
                this.boardType,
                memberDTO.memberToEntity(),
                toEntity(fileDTOS)
        );
    }

    public List<SuggestFile> toEntity(List<FileDTO> fileDTOS) {
        List<SuggestFile> suggestFiles = new ArrayList<>();
        fileDTOS.forEach(
                fileDTO -> {
                    suggestFiles.add(fileDTO.suggestFileToEntity());
                }
        );
        return suggestFiles;
    }
}
