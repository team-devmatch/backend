package com.team03.project1.domain.festival.repository;

import com.team03.project1.domain.festival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendFestivalRepository extends JpaRepository<FestivalEntity, Long> {
    // 하단(랜덤으로 4개 뽑기)
    @Query(value = """
        SELECT *
        FROM festival
        WHERE CAST(end_date AS DATE) >= CURRENT_DATE
        AND image_url IS NOT NULL
        ORDER BY RANDOM()
        LIMIT 5;
        """, nativeQuery = true)
    List<FestivalEntity> findRecommendFestival();
}
