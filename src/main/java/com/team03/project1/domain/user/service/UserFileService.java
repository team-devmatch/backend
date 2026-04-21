package com.team03.project1.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UserFileService {
    private static final String DIR ="uploads/user/";
    // 이미지 저장
    public String saveFile(MultipartFile multipartFile){
        String profile_image = null;
        if(multipartFile == null || multipartFile.isEmpty())
            profile_image = "nan";
        else{
            profile_image = UUID.randomUUID().toString() + "-" //UUID : 전세계적으로 유일한 값을 뽑아낸다.
                    + multipartFile.getOriginalFilename();
            Path path = Paths.get(DIR + profile_image);
            try {
                Files.createDirectories(path.getParent()); //폴더 생성
                multipartFile.transferTo(path); //저장
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return profile_image; //변경되어 있는 파일이름 return
    }
}
