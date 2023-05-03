package com.app.projectjar.entity.file;

import com.app.projectjar.entity.board.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
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
