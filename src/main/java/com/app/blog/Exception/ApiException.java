package com.app.blog.Exception;

public class ApiException extends RuntimeException {

    String message;

    public ApiException(String message) {
        super(String.format("%s", message));
    }

    public ApiException() {
        super();
    }

}