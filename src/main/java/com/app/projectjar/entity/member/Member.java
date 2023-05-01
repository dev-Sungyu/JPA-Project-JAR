package com.app.projectjar.entity.member;

import com.app.projectjar.audit.Period;
import com.app.projectjar.type.MemberType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
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
    private MemberType memberStatus;

}
