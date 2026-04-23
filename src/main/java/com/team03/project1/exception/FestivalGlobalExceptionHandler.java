package com.team03.project1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FestivalGlobalExceptionHandler {
    @ExceptionHandler(FestivalNotFoundException.class)
    public ResponseEntity<ProblemDetail> festivalNotFoundHandler(FestivalNotFoundException festivalNotFoundException){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("축제 없음");
        problemDetail.setDetail(festivalNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }
}
