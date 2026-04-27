package com.team03.project1.exception.festival;

public class FestivalNotFoundException extends RuntimeException{
    public FestivalNotFoundException(){}
    public FestivalNotFoundException(String msg){
        super(msg);
    }
}
