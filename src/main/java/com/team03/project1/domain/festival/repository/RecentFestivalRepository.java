package com.team03.project1.domain.festival.repository;

import com.team03.project1.domain.festival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecentFestivalRepository extends JpaRepository<FestivalEntity, Long> {
    // 배너 밑 카드 섹션(현재 날짜 기준으로 곧 시작하는 축제 7개)
    @Query(value = """
        SELECT *
        FROM festival
        WHERE CAST(start_date AS DATE) >= CURRENT_DATE and image_url IS NOT NULL
        ORDER BY start_date ASC
        LIMIT 7
        """, nativeQuery = true)
    List<FestivalEntity> findRecentFestival();
}

