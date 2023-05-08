package com.app.projectjar.domain.dto.file;

import com.app.projectjar.domain.dto.member.MemberDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;

    private MemberDTO member;

}
