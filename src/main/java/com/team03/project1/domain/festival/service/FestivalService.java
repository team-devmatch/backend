package com.team03.project1.domain.festival.service;

import com.team03.project1.domain.festival.dto.FestivalDto;
import com.team03.project1.domain.festival.repository.MonthFestivalRepository;
import com.team03.project1.domain.festival.repository.RecentFestivalRepository;
import com.team03.project1.domain.festival.repository.RecommendFestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalService {
    @Autowired
    private RecentFestivalRepository recentFestivalRepository;
    @Autowired
    private MonthFestivalRepository monthFestivalRepository;
    @Autowired
    private RecommendFestivalRepository recommendFestivalRepository;

    // 배너 밑 카드 섹션(현재 날짜 기준으로 곧 시작하는 축제 7개)
    public List<FestivalDto> getRecentFestival() {

        return recentFestivalRepository.findRecentFestival()
                .stream()
                .map(f -> new FestivalDto(
                        f.getFestivalId(),
                        f.getName(),
                        f.getImageUrl(),
                        f.getStartDate(),
                        f.getEndDate()
                ))
                .toList();
    }
    // 가운데(오늘 기준 최근 7일 ~ 앞으로 30일 사이 시작하는, 아직 안 끝난 축제를 시작일 빠른순으로 4개 가져온다. -> 이달의 축제 느낌)
    public List<FestivalDto> getMonthFestival() {

        return monthFestivalRepository.findMonthlyFestival()
                .stream()
                .map(f -> new FestivalDto(
                        f.getFestivalId(),
                        f.getName(),
                        f.getImageUrl(),
                        f.getStartDate(),
                        f.getEndDate()
                ))
                .toList();
    }
    // 하단(랜덤으로 4개 뽑기)
    public List<FestivalDto> getRecommendFestival() {

        return recommendFestivalRepository.findRecommendFestival()
                .stream()
                .map(f -> new FestivalDto(
                        f.getFestivalId(),
                        f.getName(),
                        f.getImageUrl(),
                        f.getStartDate(),
                        f.getEndDate()
                ))
                .toList();
    }
}
