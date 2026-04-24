package com.team03.project1.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {

    private String category;
    private String title;
    private String content;
    private MultipartFile image;
}
