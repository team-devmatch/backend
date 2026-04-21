package com.team03.project1.exception;

//닉네임 존재
public class NickNameDuplicateException extends RuntimeException{
    public NickNameDuplicateException(){}
    public NickNameDuplicateException(String msg){
        super(msg);
    }
}
