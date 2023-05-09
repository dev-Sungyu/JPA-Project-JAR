package com.app.projectjar.domain.suggest;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.type.BoardType;
import com.app.projectjar.domain.member.MemberDTO;
import lombok.Builder;
import lombok.Data;

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
}
