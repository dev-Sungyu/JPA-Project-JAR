package com.app.projectjar.entity.file;


import com.app.projectjar.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_MEMBER_FILE")
public class MemberFile {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String imgOriginalName;
    private String imgUuid;
    private String imgPath;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
