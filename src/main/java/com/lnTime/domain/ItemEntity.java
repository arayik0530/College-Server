package com.lnTime.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "items", indexes = {
        @Index(name = "items_title_IDX", columnList = "title")})
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 3000)
    private String description;

    @Column
    private String text;

    @JoinColumn(name = "sub_category_id", nullable = false)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubCategoryEntity subCategory;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ImageEntity> images;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + subCategory +
                '}';
    }
}