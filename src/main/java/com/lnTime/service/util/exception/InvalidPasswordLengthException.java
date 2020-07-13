package com.lnTime.service.util.exception;

public class InvalidPasswordLengthException extends RuntimeException{

    public InvalidPasswordLengthException(int MIN_PASS_LENGTH) {
        super("The minimum length of a password must be more or equal to " + MIN_PASS_LENGTH);
    }
}
