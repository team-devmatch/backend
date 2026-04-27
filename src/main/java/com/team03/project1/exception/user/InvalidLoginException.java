package com.team03.project1.exception.user;

//로그인 실패
public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException(){}
    public InvalidLoginException(String msg){
        super(msg);
    }
}