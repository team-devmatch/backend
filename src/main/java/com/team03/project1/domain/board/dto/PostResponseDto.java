package com.team03.project1.domain.board.dto;

import com.team03.project1.domain.board.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long postId; //게시글 번호
    private String category; //잡담/후기
    private String title;
    private String content; //본문
    private String imageUrl;
    private int likeCount;
    private int viewCount;
    private int commentCount; //댓글 수
    private String nickname; //작성자 닉네임
    private String profileImage; //작성자 프로필 사진
    private LocalDateTime createdAt; //작성일
    private LocalDateTime updatedAt; //수정일
    private boolean isLiked; //내가 좋아요 눌렀는지

    public static PostResponseDto from(PostEntity post, int commentCount, boolean isLiked){
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .category(post.getCategory())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .likeCount(post.getLikeCount())
                .viewCount(post.getViewCount())
                .commentCount(commentCount)
                .nickname(post.getUser().getNickname())
                .profileImage(post.getUser().getProfile_image())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .isLiked(isLiked)
                .build();
    }

}
