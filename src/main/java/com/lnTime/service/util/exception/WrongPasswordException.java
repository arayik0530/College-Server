package com.lnTime.service.util.exception;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException() {
        super("Wrong password");
    }
}
