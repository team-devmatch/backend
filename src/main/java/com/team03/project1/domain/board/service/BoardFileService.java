package com.team03.project1.domain.board.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class BoardFileService {

    private static final String DIR = "uploads/board/";

    public String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;

        String fileName = UUID.randomUUID().toString() + "-"
                + file.getOriginalFilename();
        Path path = Paths.get(DIR + fileName);
        try {
            Files.createDirectories(path.getParent());
            file.transferTo(path);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
        return fileName;
    }
}