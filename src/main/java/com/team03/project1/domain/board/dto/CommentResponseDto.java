package com.team03.project1.domain.board.dto;

import com.team03.project1.domain.board.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private Long commentId; //댓글 번호
    private String content; //댓글 내용
    private String nickname; //작성자 닉네임
    private String profileImage; //작성자 프로필 사진
    private LocalDateTime createdAt; //작성일
    private LocalDateTime updatedAt; //수정일

    public static CommentResponseDto from(CommentEntity comment){
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .nickname(comment.getUser().getNickname())
                .profileImage(comment.getUser().getProfile_image())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }


}
