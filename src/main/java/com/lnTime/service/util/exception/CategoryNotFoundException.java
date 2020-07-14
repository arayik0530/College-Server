package com.lnTime.service.util.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id) {
        super(id.toString());
    }
}
