package com.team03.project1.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {}
    public UnauthorizedException(String msg) {
        super(msg);
    }
}