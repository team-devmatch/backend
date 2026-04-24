package com.team03.project1.controller.festival;

import com.team03.project1.domain.festival.dto.FestivalDetailDto;
import com.team03.project1.domain.festival.dto.FestivalDto;
import com.team03.project1.domain.festival.dto.FestivalInfoDto;
import com.team03.project1.domain.festival.entity.FestivalEntity;
import com.team03.project1.domain.festival.repository.FestivalRepository;
import com.team03.project1.domain.festival.service.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/festivals")
public class FestivalController {
    @Autowired
    private FestivalService festivalService;
    @Autowired
    private FestivalRepository festivalRepository;

    //@GetMapping("/test")
    public ResponseEntity<List<FestivalEntity>> test() {
        return ResponseEntity.ok(festivalRepository.findAll(Sort.by("festivalId")));
    }

    // 배너 밑 카드 섹션(현재 날짜 기준으로 곧 시작하는 축제 7개)
    @GetMapping("/recent")
    public ResponseEntity<List<FestivalDto>> getRecentFestival() {
        System.out.print("통신 성공");
        List<FestivalDto> test = festivalService.getRecentFestival();
        System.out.print("테스트 출력"+test);
        return ResponseEntity.ok(festivalService.getRecentFestival());
    }
    // 가운데(오늘 기준 최근 7일 ~ 앞으로 30일 사이 시작하는, 아직 안 끝난 축제를 시작일 빠른순으로 4개 가져온다. -> 이달의 축제 느낌)
    @GetMapping("/month")
    public ResponseEntity<List<FestivalDto>> getMonthFestival() {
        return ResponseEntity.ok(festivalService.getMonthFestival());
    }
    // 하단(랜덤으로 4개 뽑기)
    @GetMapping("/recommend")
    public ResponseEntity<List<FestivalDto>> getRecommendFestival() {
        return ResponseEntity.ok(festivalService.getRecommendFestival());
    }

    // 상세정보
    @GetMapping("/{id}")
    public ResponseEntity<FestivalDetailDto> getDetailFestival(@PathVariable("id") Long id) {
        return ResponseEntity.ok(festivalService.getDetailFestival(id));
    }

    //축제정보 페이지(전체,필터,검색)
    @GetMapping
    public ResponseEntity<Page<FestivalInfoDto>> getFestivals(
            @RequestParam(required = false) String theme, //필터
            @RequestParam(required = false) String keyword, //검색
            @PageableDefault(size = 12, sort = "startDate", direction = Sort.Direction.ASC)
            Pageable pageable //전체 보기(페이지 구성, 몇 개씩, 몇 페이지)
    ) {
        System.out.print("page: " + pageable.getPageNumber());
        System.out.print("size: " + pageable.getPageSize());
        return ResponseEntity.ok(festivalService.getFestivals(theme, keyword, pageable));
    }
}
