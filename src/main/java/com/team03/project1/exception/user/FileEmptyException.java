package com.team03.project1.exception.user;

public class FileEmptyException extends RuntimeException{
    public FileEmptyException(){}
    public FileEmptyException(String msg){
        super(msg);
    }
}
