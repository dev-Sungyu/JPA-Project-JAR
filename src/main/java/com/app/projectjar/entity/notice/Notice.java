package com.app.projectjar.entity.notice;

import com.app.projectjar.audit.Period;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter @ToString
@Table(name ="TBL_NOTICE")
public class Notice extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
}
