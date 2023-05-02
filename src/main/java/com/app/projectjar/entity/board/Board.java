package com.app.projectjar.entity.board;


import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.file.File;
import com.app.projectjar.type.Document;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Board extends Period{
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Embedded
    private Document document;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID")
    private List<File> files = new ArrayList<>();

}
