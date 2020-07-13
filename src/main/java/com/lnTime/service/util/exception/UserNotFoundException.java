package com.lnTime.service.util.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(id.toString());
    }

    public UserNotFoundException(String mail) {
        super(mail);
    }
}