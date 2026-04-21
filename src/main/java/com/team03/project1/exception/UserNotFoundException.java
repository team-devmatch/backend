package com.team03.project1.exception;

// 사용자 없음
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (){}
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
