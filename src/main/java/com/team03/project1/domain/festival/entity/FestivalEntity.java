package com.team03.project1.domain.festival.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="festival", schema = "public")
public class FestivalEntity {
    // 기본 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "festival_id", nullable = false)
    private Long festivalId;
    //외래키 설정
    @OneToOne(mappedBy = "festivalEntity", fetch = FetchType.LAZY)
    private FestivalDetailEntity festivalDetailEntity;

    @Column(name = "content_id", nullable = false, length = 50)
    private String contentId;
    @Column(nullable = false, length = 100)
    private String source;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(length = 300)
    private String address;
    @Column(name = "start_date", length = 20)
    private String startDate;
    @Column(name = "end_date", length = 20)
    private String endDate;
    @Column(name = "image_url", length = 500)
    private String imageUrl;
    @Column(length = 100)
    private String theme;
    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

}
