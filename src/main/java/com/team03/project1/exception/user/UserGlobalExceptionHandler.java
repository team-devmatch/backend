package com.team03.project1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserGlobalExceptionHandler {
    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<ProblemDetail> usersDuplicateHandler(UserDuplicateException userDuplicateException){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("id 존재");
        problemDetail.setDetail(userDuplicateException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> illegalArgumentHandler(IllegalArgumentException illegalArgumentException){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("비밀번호 불일치");
        problemDetail.setDetail(illegalArgumentException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }
    @ExceptionHandler(NickNameDuplicateException.class)
    public ResponseEntity<ProblemDetail> nickNameHandler(NickNameDuplicateException nickNameDuplicateException){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("닉네임 존재");
        problemDetail.setDetail(nickNameDuplicateException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }
    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ProblemDetail> invalidHandler(InvalidLoginException invalidLoginException){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("로그인 실패");
        problemDetail.setDetail(invalidLoginException.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> userNotFoundHandler(UserNotFoundException userNotFoundException){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("사용자 없음");
        problemDetail.setDetail(userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }
}
