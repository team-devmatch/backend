package com.team03.project1.domain.board.service;

import com.team03.project1.domain.board.dto.PostRequestDto;
import com.team03.project1.domain.board.dto.PostResponseDto;
import com.team03.project1.domain.board.dto.CommentRequestDto;
import com.team03.project1.domain.board.dto.CommentResponseDto;
import com.team03.project1.domain.board.entity.*;
import com.team03.project1.domain.board.repository.*;
import com.team03.project1.domain.user.entity.UserEntity;
import com.team03.project1.domain.user.repository.UserRepository;
import com.team03.project1.exception.CommentNotFoundException;
import com.team03.project1.exception.PostNotFoundException;
import com.team03.project1.exception.UnauthorizedException;
import com.team03.project1.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //final 필드들의 생성자 자동 생성
public class BoardService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final BoardFileService boardFileService;

    //게시글 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts(String category, String email){
        UserEntity user = (email != null)
                ? userRepository.findByEmail(email).orElse(null)
                : null;

        List<PostEntity> posts = (category == null || category.isEmpty())
                ? postRepository.findAllByOrderByCreatedAtDesc()
                : postRepository.findByCategoryOrderByCreatedAtDesc(category);

        return posts.stream()
                .map(post -> {
                    int commentCount = commentRepository.countByPost(post);
                    boolean isLiked = user != null &&
                            postLikeRepository.existsByPostAndUser(post, user);
                    return PostResponseDto.from(post, commentCount, isLiked);
                })
                .collect(Collectors.toList());
    }

    //게시글 상세 조회
    @Transactional
    public PostResponseDto getPost(Long postId, String email){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));
        post.increaseViewCount();
        UserEntity user = (email != null)
                ? userRepository.findByEmail(email).orElse(null)
                : null;
        int commentCount = commentRepository.countByPost(post);
        boolean isLiked = user != null &&
                postLikeRepository.existsByPostAndUser(post, user);

        return PostResponseDto.from(post, commentCount, isLiked);
    }

    //게시글 작성
    @Transactional
    public PostResponseDto createPost(PostRequestDto request, String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("유저를 찾을 수 없습니다."));

        String imageUrl = boardFileService.saveFile(request.getImage());

        PostEntity post = PostEntity.builder()
                .user(user)
                .category(request.getCategory())
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(imageUrl)
                .build();
        postRepository.save(post);
        return PostResponseDto.from(post,0,false);
    }

    //게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto request, String email){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("게시글을 찾을 수 없습니다."));
        if(!post.getUser().getEmail().equals(email)){
            throw new UnauthorizedException("본인 게시글만 수정 할 수 있습니다.");
        }

        String imageUrl = request.getImage() != null && !request.getImage().isEmpty()
                ? boardFileService.saveFile(request.getImage())
                : post.getImageUrl();

        post.update(request.getTitle(), request.getContent(),
                request.getCategory(), imageUrl);
        int commentCount = commentRepository.countByPost(post);
        boolean isLiked = postLikeRepository.existsByPostAndUser(
                post, post.getUser());
        return PostResponseDto.from(post, commentCount, isLiked);
    }

    //게시글 삭제
    @Transactional
    public void deletePost(Long postId, String email){
          PostEntity post = postRepository.findById(postId)
                  .orElseThrow(()->new PostNotFoundException("게시글을 찾을 수 없습니다."));
          if(!post.getUser().getEmail().equals(email)){
              throw new UnauthorizedException("본인 게시글만 삭제 할 수 있습니다.");
          }
          postRepository.delete(post);
    }

    //좋아요 토글
    @Transactional
    public boolean toggleLike(Long postId, String email){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(()->new PostNotFoundException("게시글을 찾을 수 없습니다."));
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("유저를 찾을 수 없습니다."));
        if(postLikeRepository.existsByPostAndUser(post, user)){
            PostLikeEntity like = postLikeRepository
                    .findByPostAndUser(post, user).get();
            postLikeRepository.delete(like);
            post.decreaseLikeCount();
            return false;
        }else{
            PostLikeEntity like = PostLikeEntity.builder()
                    .post(post)
                    .user(user)
                    .build();
            postLikeRepository.save(like);
            post.increaseLikeCount();
            return true;
        }
    }

    //댓글 목록 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long postId){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(()->new PostNotFoundException("게시글을 찾을 수 없습니다."));
        List<CommentEntity> comments = commentRepository.findByPostOrderByCreatedAtDesc(post);
        return comments.stream()
                .map(comment -> CommentResponseDto.from(comment))
                .collect(Collectors.toList());
    }

    //댓글 작성
    @Transactional
    public CommentResponseDto createComment(Long postId,
            CommentRequestDto request, String email){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(()->new PostNotFoundException("게시글을 찾을 수 없습니다."));
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("유저를 찾을 수 없습니다."));
        CommentEntity comment = CommentEntity.builder()
                .post(post)
                .user(user)
                .content(request.getContent())
                .build();
        commentRepository.save(comment);
        return CommentResponseDto.from(comment);
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId,
             CommentRequestDto request, String email) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("댓글을 찾을 수 없습니다."));
        if (!comment.getUser().getEmail().equals(email)) {
            throw new UnauthorizedException("본인 댓글만 수정 할 수 있습니다.");
        }
        comment.update(request.getContent());
        return CommentResponseDto.from(comment);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, String email){
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(()->new CommentNotFoundException("댓글을 찾을 수 없습니다."));
        if(!comment.getUser().getEmail().equals(email)){
            throw new UnauthorizedException("본인 댓글만 삭제 할 수 있습니다.");
        }
        commentRepository.delete(comment);
    }
}
