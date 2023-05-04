package com.app.projectjar.entity.member;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.file.File;
import com.app.projectjar.entity.file.MemberFile;
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

    public Member(String memberEmail, String memberPassword, String memberPhoneNumber, String memberName, String memberNickname) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }
}
