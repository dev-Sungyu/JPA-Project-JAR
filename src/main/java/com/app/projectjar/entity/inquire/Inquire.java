package com.app.projectjar.entity.inquire;


import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.AnswerType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_INQUIRE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquire extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String inquireTitle;
    @NotNull private String inquireContent;
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'UNANSWERED'")
    private AnswerType answerType;

    public void setId(Long id){ this.id = id; }

    public void setInquireTitle(String inquireTitle){ this.inquireTitle = inquireTitle; }

    public void setInquireContent(String inquireContent){ this.inquireContent = inquireContent; }

//  한 사람당 여러가지를 문의할 수 있으니까 ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "inquire")
    private Answer answer;

    @Builder
    public Inquire(Long id, String inquireTitle, String inquireContent, AnswerType answerType, Member member) {
        this.id = id;
        this.inquireTitle = inquireTitle;
        this.inquireContent = inquireContent;
        this.answerType = answerType;
        this.member = member;
    }
}
