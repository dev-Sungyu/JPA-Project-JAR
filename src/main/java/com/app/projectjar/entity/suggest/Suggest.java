package com.app.projectjar.entity.suggest;

import com.app.projectjar.audit.Period;
import com.app.projectjar.audit.Title;
import com.app.projectjar.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_SUGGEST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Suggest extends Title {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "suggest", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<SuggestReply> suggestReplies = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
