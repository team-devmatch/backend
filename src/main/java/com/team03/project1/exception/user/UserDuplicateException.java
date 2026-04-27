package com.team03.project1.exception.user;

//id 존재
public class UserDuplicateException extends RuntimeException{
    public UserDuplicateException(){}
    public UserDuplicateException(String msg){
        super(msg);
    }
}
