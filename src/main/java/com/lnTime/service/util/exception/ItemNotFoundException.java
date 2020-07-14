package com.lnTime.service.util.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(Long id) {
        super(id.toString());
    }
}
