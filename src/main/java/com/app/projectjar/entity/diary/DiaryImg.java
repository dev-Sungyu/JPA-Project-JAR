package com.app.projectjar.entity.diary;

import com.app.projectjar.audit.Period;
import com.app.projectjar.type.Image;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_DIARY_IMG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryImg extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Embedded
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIARY_ID")
    private Diary diary;
}
