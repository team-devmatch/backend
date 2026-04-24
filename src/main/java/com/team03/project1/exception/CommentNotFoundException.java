package com.team03.project1.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {}
    public CommentNotFoundException(String msg) {
        super(msg);
    }
}