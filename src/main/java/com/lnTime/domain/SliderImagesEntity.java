package com.lnTime.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "slider_images")
public class SliderImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "picture")
    @Lob
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] picture;
}