package com.app.projectjar.entity.member;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.type.BedgeType;
import com.app.projectjar.type.MemberType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Member extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Column(unique = true)
    @NotNull private String memberEmail;
    @NotNull private String memberPassword;
    @Column(unique = true)
    @NotNull private String memberPhoneNumber;
    @NotNull private String memberName;
    @NotNull private String memberNickname;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ENABLE'")
    private MemberType memberStatus;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ZERO'")
    private BedgeType bedgeType;

    public Member(String memberEmail, String memberPassword, String memberPhoneNumber, String memberName, String memberNickname, MemberType memberStatus, BedgeType bedgeType) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberStatus = memberStatus;
        this.bedgeType = bedgeType;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private MemberFile memberFile;
}
