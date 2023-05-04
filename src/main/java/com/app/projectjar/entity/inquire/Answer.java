package com.app.projectjar.entity.inquire;

import com.app.projectjar.audit.Period;
import com.app.projectjar.type.AnswerType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name ="TBL_ANSWER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Answer extends Period {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String answerContent;
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'UNANSWERED'")
    private AnswerType answerStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INQUIRE_ID")
    private Inquire inquire;
}
