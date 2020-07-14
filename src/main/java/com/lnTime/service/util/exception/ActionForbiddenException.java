package com.lnTime.service.util.exception;

public class ActionForbiddenException extends RuntimeException{
    public ActionForbiddenException() {
        super("This action is forbidden.");
    }
}
