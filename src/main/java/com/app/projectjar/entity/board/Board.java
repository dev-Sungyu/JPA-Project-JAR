package com.app.projectjar.entity.board;


import com.app.projectjar.audit.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Board extends Period{
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String boardTitle;
    private String boardContent;


}
