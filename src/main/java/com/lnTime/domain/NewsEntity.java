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
@Table(name = "news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String title;

//    @JoinColumn(name = "user_id", nullable = false)
//    @ManyToOne
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private UserEntity user;

//    @JoinColumn(name = "sub_category_id", nullable = false)
//    @ManyToOne
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private SubCategoryEntity subCategory;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ImageEntity> images;

//    @OneToMany(cascade = CascadeType.REMOVE)
//    private List<SmallImageEntity> smallImages;

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
        return "Item{" +
                "id=" + id +
                ", description='" + description +
                ", title='" + title +
                '}';
    }
}
