package com.team03.project1.domain.festival.repository;

import com.team03.project1.domain.festival.entity.FestivalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FestivalRepository extends JpaRepository<FestivalEntity, Long> {
    @Query("""
        SELECT f
        FROM FestivalEntity f
        WHERE CAST(f.endDate AS DATE) >= CURRENT_DATE
    """)
    Page<FestivalEntity> findAll(Pageable pageable);

    @Query("SELECT f FROM FestivalEntity f WHERE f.name LIKE %:keyword%")
    Page<FestivalEntity> search(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT f FROM FestivalEntity f WHERE f.theme = :theme")
    Page<FestivalEntity> filterByTheme(@Param("theme") String theme, Pageable pageable);

    @Query("""
        SELECT f FROM FestivalEntity f
        WHERE f.name LIKE %:keyword%
        AND f.theme = :theme
    """)
    Page<FestivalEntity> searchAndFilter(@Param("keyword") String keyword,
                                   @Param("theme") String theme,
                                   Pageable pageable);
}