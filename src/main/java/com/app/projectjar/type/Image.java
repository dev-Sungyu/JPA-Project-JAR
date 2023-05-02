package com.app.projectjar.type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter @ToString
public class Image {
    private String imageOriginalName;
    private String imageUuid;
    private String imagePath;
}
