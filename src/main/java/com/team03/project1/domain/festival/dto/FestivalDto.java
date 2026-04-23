package com.team03.project1.domain.festival.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FestivalDto {
    private Long festivalId;
    private String name;
    private String imageUrl;
    private String startDate;
    private String endDate;
}
