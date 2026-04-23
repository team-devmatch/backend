package com.team03.project1.domain.board.repository;
// 좋아요 존재 여부 확인, 좋아요 단건 조회
import com.team03.project1.domain.board.entity.PostEntity;
import com.team03.project1.domain.board.entity.PostLikeEntity;
import com.team03.project1.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {

    Optional<PostLikeEntity> findByPostAndUser(PostEntity post, UserEntity user);

    boolean existsByPostAndUser(PostEntity post, UserEntity user);
}
