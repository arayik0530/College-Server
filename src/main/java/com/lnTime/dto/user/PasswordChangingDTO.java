package com.lnTime.dto.user;

import lombok.Data;

@Data
public class PasswordChangingDTO {
    private String email;
    private String oldPassword;
    private String newPassword;

}