package com.team03.project1.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {}
    public PostNotFoundException(String msg) {
        super(msg);
    }
}