package com.team03.project1.domain.festival.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="festival_detail")
public class FestivalDetailEntity {
    // 상세정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id", nullable = false)
    private Long detailId;
    //외래키 설정
    @OneToOne
    @JoinColumn(name = "festival_id", nullable = false)
    private FestivalEntity festivalEntity;

    private String description;
    @Column(length = 50)
    private String tel;
    @Column(length = 500)
    private String homepage;
    @Column(length = 200)
    private String parking;
    private Double latitude;
    private Double longitude;
    @Column(name = "sub_image_url", length = 500)
    private String subImageUrl;

}
