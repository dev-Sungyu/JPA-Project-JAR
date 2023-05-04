package com.app.projectjar.entity.file.member;


import com.app.projectjar.entity.file.Files;
import com.app.projectjar.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_MEMBER_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFile extends Files {

//    나중에 유지보수 편하게 하기 위해 엔티티 만듬
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
