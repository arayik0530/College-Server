package com.lnTime.dto.user;

import com.lnTime.domain.UserEntity;
import lombok.Getter;

@Getter
public class UserRegistrationDTO {

    private String mail;
    private String password;
    private String firstName;
    private String lastName;

    public UserEntity toEntity(){
        UserEntity user = new UserEntity();

        user.setMail(this.mail);
        user.setLastName(this.lastName);
        user.setFirstName(this.firstName);

        return user;
    }
}