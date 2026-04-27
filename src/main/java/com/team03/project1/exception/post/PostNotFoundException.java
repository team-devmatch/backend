package com.team03.project1.exception.post;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {}
    public PostNotFoundException(String msg) {
        super(msg);
    }
}