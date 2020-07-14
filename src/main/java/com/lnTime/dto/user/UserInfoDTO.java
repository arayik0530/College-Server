package com.lnTime.dto.user;

import com.lnTime.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Stream;

@Getter
@Setter
public class UserInfoDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String mail;

    private String phone;

    private String[] roles;

    public static UserInfoDTO mapFromEntity(UserEntity user) {

        if (user == null) {
            return null;
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.id = user.getId();
        userInfoDTO.firstName = user.getFirstName();
        userInfoDTO.lastName = user.getLastName();
        userInfoDTO.mail = user.getMail();
        userInfoDTO.phone = user.getPhone();

        userInfoDTO.roles = Stream.of(user.getRoles())
                .map(Object::toString).toArray(String[]::new);

        return userInfoDTO;

    }

    public UserEntity toEntity( UserEntity user) {

        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setMail(this.mail);
        user.setPhone(this.phone);

        return user;
    }
}