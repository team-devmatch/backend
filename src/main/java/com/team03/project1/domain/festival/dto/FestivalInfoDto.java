package com.team03.project1.domain.festival.dto;

import com.team03.project1.domain.festival.entity.FestivalEntity;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FestivalInfoDto {
    private Long festivalId;
    private String name;
    private String imageUrl;
    private String startDate;
    private String endDate;
    private String theme;

    public FestivalInfoDto(FestivalEntity festivalEntity) {
        BeanUtils.copyProperties(festivalEntity, this);
    }
}
