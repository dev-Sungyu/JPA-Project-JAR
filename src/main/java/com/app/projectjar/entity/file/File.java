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
@Getter @ToString(callSuper = true)
@Table(name = "TBL_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;
}
