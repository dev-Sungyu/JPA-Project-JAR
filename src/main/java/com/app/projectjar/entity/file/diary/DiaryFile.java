package com.app.projectjar.entity.file.diary;

import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.file.Files;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_DIARY_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

}
