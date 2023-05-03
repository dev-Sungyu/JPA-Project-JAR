package com.app.projectjar.entity.file;


import com.app.projectjar.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = "member")
@Table(name = "TBL_MEMBER_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFile {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;

//    나중에 유지보수 편하게 하기 위해 엔티티 만듬
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
