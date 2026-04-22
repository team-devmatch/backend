package com.team03.project1.controller.festival;

import com.team03.project1.domain.festival.entity.FestivalDetailEntity;
import com.team03.project1.domain.festival.entity.FestivalEntity;
import com.team03.project1.domain.festival.repository.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/festivals")
public class FestivalController {
    @Autowired
    private FestivalRepository festivalRepository;
    @Autowired
    private FestivalDetailEntity festivalDetailEntity;
    @GetMapping("/test")
    public ResponseEntity<List<FestivalEntity>> test() {
        return ResponseEntity.ok(festivalRepository.findAll(Sort.by("festivalId")));
    }
}
