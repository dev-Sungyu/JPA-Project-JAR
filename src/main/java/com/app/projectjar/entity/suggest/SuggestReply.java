package com.app.projectjar.entity.suggest;

import com.app.projectjar.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_SUGGEST_REPLY")
public class SuggestReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String suggestReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Suggest suggest;
}
