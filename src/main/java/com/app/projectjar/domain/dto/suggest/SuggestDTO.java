package com.app.projectjar.domain.dto.suggest;

import com.app.projectjar.domain.dto.file.FileDTO;
import com.app.projectjar.domain.dto.member.MemberDTO;
import com.app.projectjar.type.BoardType;
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
    private List<FileDTO> fIleDTOS;
}
