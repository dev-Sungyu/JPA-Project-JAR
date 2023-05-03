package com.app.projectjar.entity.inquire;


import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_INQUIRE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquire extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String inquireTitle;
    private String inquireContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
