package com.app.projectjar.entity.challenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.file.File;
import com.app.projectjar.type.ChallengeType;
import com.app.projectjar.type.Document;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private ChallengeType challengeStatus;

    @Embedded
    private Document document;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "challenge",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<File> files = new ArrayList<>();
}
