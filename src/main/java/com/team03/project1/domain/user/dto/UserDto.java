package com.team03.project1.domain.user.dto;

import com.team03.project1.domain.user.entity.UserEntity;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String profileImage;
    private String email;
    private String nickname;

    public UserDto(UserEntity userEntity){
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();

        //이미지 url 설정
        String fileName = userEntity.getProfileImage();

        if(fileName == null){
            this.profileImage = "/images/default.png";
        } else {
            this.profileImage = "/uploads/user/" + fileName;
        }
    }
}

