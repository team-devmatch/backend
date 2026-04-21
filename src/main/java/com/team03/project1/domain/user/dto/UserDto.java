package com.team03.project1.domain.user.dto;

import com.team03.project1.domain.user.entity.UserEntity;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String profile_image;
    private String email;
    private String nickname;

    public UserDto(UserEntity userEntity){
        BeanUtils.copyProperties(userEntity, this);

        //이미지 url 설정
        String fileName = userEntity.getProfile_image();

        if(fileName == null || fileName.equals("default.png")){
            this.profile_image = "/images/default.png";
        } else {
            this.profile_image = "/uploads/user/" + fileName;
        }
    }
}

