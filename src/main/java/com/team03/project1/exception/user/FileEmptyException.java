package com.team03.project1.exception.user;

// 파일 없음
public class FileEmptyException extends RuntimeException{
    public FileEmptyException(){}
    public FileEmptyException(String msg){
        super(msg);
    }
}
