package com.app.projectjar.entity.file.suggest;

import com.app.projectjar.entity.file.Files;
import com.app.projectjar.entity.suggest.Suggest;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter @ToString(callSuper = true, exclude = "suggest")
@Table(name = "TBL_SUGGEST_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuggestFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    private Suggest suggest;

    public SuggestFile(String fileOriginalName, String fileUuid, String filePath, Suggest suggest) {
        super(fileOriginalName, fileUuid, filePath);
        this.suggest = suggest;
    }

    @Builder
    public SuggestFile(Long id, String fileOriginalName, String fileUuid, String filePath, Suggest suggest) {
        super(id, fileOriginalName, fileUuid, filePath);
        this.suggest = suggest;
    }

    public void setSuggest(Suggest suggest) {
        this.suggest = suggest;

        if(!suggest.getSuggestFiles().contains(this)){
            suggest.getSuggestFiles().add(this);
        }
    }
}
