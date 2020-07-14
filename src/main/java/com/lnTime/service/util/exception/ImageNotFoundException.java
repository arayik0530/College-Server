package com.lnTime.service.util.exception;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(Long id) {
        super(id.toString());
    }
}
