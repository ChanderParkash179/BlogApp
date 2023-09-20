package com.app.blog.Exception;
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}