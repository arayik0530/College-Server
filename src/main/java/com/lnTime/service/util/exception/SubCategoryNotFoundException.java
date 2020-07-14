package com.lnTime.service.util.exception;

public class SubCategoryNotFoundException extends RuntimeException{
    public SubCategoryNotFoundException(Long id) {
        super(id.toString());
    }
}