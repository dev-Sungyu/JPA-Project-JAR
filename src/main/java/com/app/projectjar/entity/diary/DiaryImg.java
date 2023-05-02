package com.app.projectjar.entity.diary;

import com.app.projectjar.audit.Image;
import com.app.projectjar.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_DIARY_IMG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryImg extends Image {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIARY_ID")
    private Diary diary;
}
