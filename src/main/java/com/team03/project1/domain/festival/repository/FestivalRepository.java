package com.team03.project1.domain.festival.repository;

import com.team03.project1.domain.festival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FestivalRepository extends JpaRepository<FestivalEntity, Long> {

    // 테마별 조회
    List<FestivalEntity> findByThemeOrderByStartDateAsc(String theme);

    // 전체 조회 (시작일 오름차순)
    List<FestivalEntity> findAllByOrderByStartDateAsc();
}