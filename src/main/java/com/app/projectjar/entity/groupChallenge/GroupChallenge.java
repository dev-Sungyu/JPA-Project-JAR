package com.app.projectjar.entity.groupChallenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.file.File;
import com.app.projectjar.type.Document;
import com.app.projectjar.type.GroupChallengeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_GROUP_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupChallenge extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private GroupChallengeType groupChallengeStatus;
    @Embedded
    private Document document;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "groupChallenge",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<File> files = new ArrayList<>();
}
