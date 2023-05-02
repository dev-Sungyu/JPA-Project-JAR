package com.app.projectjar.entity.file;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.type.BoardType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_FILE")
public class File {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String imgOriginalName;
    private String imgUuid;
    private String imgPath;

}
