package com.app.projectjar.entity.suggest;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_SUGGEST_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuggestReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String suggestReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUGGEST_ID")
    private Suggest suggest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;
}
