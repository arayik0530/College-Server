package com.lnTime.domain;


import com.lnTime.domain.util.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, name = "first_name", nullable = false)
    private String firstName;

    @Column(length = 100, name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Column(length = 15)
    private String phone;

//    @Column(nullable = false) //TODO in jwt security instead of activ must be !is_deleted
//    private Boolean active = Boolean.FALSE;

    @Column(nullable = false, name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getFirstName(), getLastName(), getMail());
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getMail(), that.getMail());
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + !isDeleted +
                ", roles=" + roles +
                '}';
    }
}