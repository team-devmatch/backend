package com.team03.project1.domain.board.repository;
// 게시글별 댓글 조회, 댓글 수 카운트
import com.team03.project1.domain.board.entity.CommentEntity;
import com.team03.project1.domain.board.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPostOrderByCreatedAtDesc(PostEntity post);

    int countByPost(PostEntity post);

}
