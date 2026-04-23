package com.team03.project1.domain.festival.repository;

import com.team03.project1.domain.festival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonthFestivalRepository extends JpaRepository<FestivalEntity, Long> {
    // 가운데(오늘 기준 최근 7일 ~ 앞으로 30일 사이 시작하는, 아직 안 끝난 축제를 시작일 빠른순으로 4개 가져온다. -> 이달의 축제 느낌)
    @Query(value = """
        SELECT * 
        FROM festival
        WHERE CAST(end_date AS DATE)  >= CURRENT_DATE
            AND CAST(start_date AS DATE) >= (CURRENT_DATE - INTERVAL '7 days')
            AND CAST(start_date AS DATE) <= (CURRENT_DATE + INTERVAL '30 days')
            AND image_url IS NOT NULL
        ORDER BY start_date ASC
        LIMIT 4
        """, nativeQuery = true)
    List<FestivalEntity> findMonthlyFestival();
}
