package com.app.projectjar.entity.file.member;


import com.app.projectjar.entity.file.Files;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.FileType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = "member")
@Table(name = "TBL_MEMBER_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFile extends Files {

//    나중에 유지보수 편하게 하기 위해 엔티티 만듬
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    public MemberFile(String fileOriginalName, String fileUuid, String filePath, FileType fileType) {
        super(fileOriginalName, fileUuid, filePath, fileType);
    }

    public MemberFile(String fileOriginalName, String fileUuid, String filePath, FileType fileType, Member member) {
        super(fileOriginalName, fileUuid, filePath,fileType);
        this.member = member;
    }

    public MemberFile(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType, Member member) {
        super(id, fileOriginalName, fileUuid, filePath, fileType);
        this.member = member;
    }
}
