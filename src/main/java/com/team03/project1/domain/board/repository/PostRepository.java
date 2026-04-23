package com.team03.project1.domain.board.repository;
// 전체 조회, 카테고리 필터 조회
import com.team03.project1.domain.board.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByOrderByCreatedAtDesc();

    List<PostEntity> findByCategoryOrderByCreatedAtDesc(String category);
}
