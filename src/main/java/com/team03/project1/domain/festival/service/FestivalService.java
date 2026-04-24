package com.team03.project1.domain.festival.service;

import com.team03.project1.domain.festival.dto.FestivalDetailDto;
import com.team03.project1.domain.festival.dto.FestivalDto;
import com.team03.project1.domain.festival.dto.FestivalInfoDto;
import com.team03.project1.domain.festival.entity.FestivalDetailEntity;
import com.team03.project1.domain.festival.entity.FestivalEntity;
import com.team03.project1.domain.festival.repository.FestivalRepository;
import com.team03.project1.domain.festival.repository.MonthFestivalRepository;
import com.team03.project1.domain.festival.repository.RecentFestivalRepository;
import com.team03.project1.domain.festival.repository.RecommendFestivalRepository;
import com.team03.project1.exception.FestivalNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private FestivalRepository festivalRepository;

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

    //상세정보
    public FestivalDetailDto getDetailFestival(Long id) {
        FestivalEntity festivalEntity = festivalRepository.findById(id)
                .orElseThrow(() -> new FestivalNotFoundException("축제를 찾을 수 없습니다"));

        FestivalDetailEntity festivalDetailEntity = festivalEntity.getFestivalDetailEntity();

        return new FestivalDetailDto(festivalEntity, festivalDetailEntity);
    }

    // 축제 정보 페이지
    public Page<FestivalInfoDto> getFestivals(String theme, String keyword, Pageable pageable){
        if (keyword != null && !keyword.isBlank()
                && theme != null && !theme.isBlank() && !theme.equals("전체")) {
            return festivalRepository.searchAndFilter(keyword, theme, pageable)
                    .map(FestivalInfoDto::new);
        }

        if (keyword != null && !keyword.isBlank()) {
            return festivalRepository.search(keyword, pageable)
                    .map(FestivalInfoDto::new);
        }

        if (theme != null && !theme.isBlank() && !theme.equals("전체")) {
            return festivalRepository.filterByTheme(theme, pageable)
                    .map(FestivalInfoDto::new);
        }

        return festivalRepository.findAll(pageable)
                .map(FestivalInfoDto::new);

    }
}