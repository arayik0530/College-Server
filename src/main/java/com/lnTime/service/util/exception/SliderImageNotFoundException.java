package com.lnTime.service.util.exception;

public class SliderImageNotFoundException extends RuntimeException{

    public SliderImageNotFoundException(Integer id) {
        super(id.toString());
    }
}
