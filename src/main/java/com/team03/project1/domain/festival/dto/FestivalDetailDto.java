package com.team03.project1.domain.festival.dto;

import com.team03.project1.domain.festival.entity.FestivalDetailEntity;
import com.team03.project1.domain.festival.entity.FestivalEntity;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FestivalDetailDto {
    //기본 정보
    private Long festivalId;
    private String name;
    private String imageUrl;
    private String address;
    private String startDate;
    private String endDate;

    //상세정보
    private String description;
    private String tel;
    private String homepage;
    private String parking;
    private Double latitude;
    private Double longitude;
    private String subImageUrl;

    public FestivalDetailDto(FestivalEntity festival, FestivalDetailEntity detail) {
        BeanUtils.copyProperties(festival, this);

        if (detail != null) {
            BeanUtils.copyProperties(detail, this);
        }
    }
}
