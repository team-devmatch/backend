package com.team03.project1.exception.post;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {}
    public CommentNotFoundException(String msg) {
        super(msg);
    }
}