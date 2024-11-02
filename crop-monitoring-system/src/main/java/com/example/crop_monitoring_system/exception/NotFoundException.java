package com.example.crop_monitoring_system.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        super();
    }
    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
