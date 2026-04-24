package com.team03.project1.controller.board;

import com.team03.project1.domain.board.dto.CommentRequestDto;
import com.team03.project1.domain.board.dto.CommentResponseDto;
import com.team03.project1.domain.board.dto.PostRequestDto;
import com.team03.project1.domain.board.dto.PostResponseDto;
import com.team03.project1.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts(
            @RequestParam(required = false) String category,
            @AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails != null ? userDetails.getUsername() : null;
        return ResponseEntity.ok(boardService.getPosts(category, email));
    }

    //게시글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails != null ? userDetails.getUsername() : null;
        return ResponseEntity.ok(boardService.getPost(postId, email));
    }

    //게시글 작성
    @SecurityRequirement(name = "JWT")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<PostResponseDto> createPost(
            @RequestParam String category,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(required = false) MultipartFile image,
            @AuthenticationPrincipal UserDetails userDetails){
        PostRequestDto request = PostRequestDto.builder()
                .category(category)
                .title(title)
                .content(content)
                .image(image)
                .build();
        return ResponseEntity.ok(
                boardService.createPost(request, userDetails.getUsername()));
    }

    //게시글 수정
    @SecurityRequirement(name = "JWT")
    @PutMapping(value = "/{postId}", consumes = "multipart/form-data")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long postId,
            @RequestParam String category,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(required = false) MultipartFile image,
            @AuthenticationPrincipal UserDetails userDetails){
        PostRequestDto request = PostRequestDto.builder()
                .category(category)
                .title(title)
                .content(content)
                .image(image)
                .build();
        return ResponseEntity.ok(
                boardService.updatePost(postId, request, userDetails.getUsername()));
    }

    //게시글 삭제
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetails userDetails){
        boardService.deletePost(postId, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    //좋아요 토글
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{postId}/like")
    public ResponseEntity<Boolean> toggleLike(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(
                boardService.toggleLike(postId, userDetails.getUsername()));
    }

    //댓글 목록 조회
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(
            @PathVariable Long postId){
        return ResponseEntity.ok(boardService.getComments(postId));
    }

    //댓글 작성
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto request,
            @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(
                boardService.createComment(postId, request, userDetails.getUsername()));
    }

    //댓글 수정
    @SecurityRequirement(name = "JWT")
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComments(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto request,
            @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(
                boardService.updateComment(commentId, request, userDetails.getUsername()));
    }

    //댓글 삭제
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetails userDetails){
        boardService.deleteComment(commentId, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
