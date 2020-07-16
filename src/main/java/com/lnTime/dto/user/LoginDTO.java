package com.lnTime.dto.user;

import lombok.Getter;

@Getter
public class LoginDTO {

    private String mail;
    private String password;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}