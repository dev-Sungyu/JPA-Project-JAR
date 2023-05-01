package com.app.projectjar.entity.notice;

import com.app.projectjar.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter @ToString
@Table(name ="TBL_NOTICE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String noticeTitle;
    @NotNull
    private String noticeContent;
}
