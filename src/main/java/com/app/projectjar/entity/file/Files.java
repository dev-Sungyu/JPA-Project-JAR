package com.app.projectjar.entity.file;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class Files {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String fileOriginalName;
    private String fileUuid;
    private String filePath;

    public Files(String fileOriginalName, String fileUuid, String filePath) {
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
    }
}
