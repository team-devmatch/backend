package com.team03.project1.domain.user.service;

import com.team03.project1.domain.user.dto.UserDto;
import com.team03.project1.domain.user.dto.UserRegDto;
import com.team03.project1.domain.user.entity.UserEntity;
import com.team03.project1.domain.user.repository.UserRepository;
import com.team03.project1.exception.user.FileEmptyException;
import com.team03.project1.exception.user.NickNameDuplicateException;
import com.team03.project1.exception.user.UserDuplicateException;
import com.team03.project1.exception.user.UserNotFoundException;
import com.team03.project1.util.CurrentUserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserFileService userFileService;
    //회원 가입
    public void insert(UserRegDto userRegDto, MultipartFile multipartFile){
        //System.out.println("파일 이름 : "+ multipartFile.getOriginalFilename());
        UserEntity userEntity = new UserEntity();
        String profile_image = "default.png";
        if(multipartFile != null && !multipartFile.isEmpty()){
            profile_image = userFileService.saveFile(multipartFile);
        } //파일 저장
        userEntity.setProfileImage(profile_image);

        boolean bool = userRepository.existsByEmail(userRegDto.getEmail()); //이메일(id) 중복 확인
        if(bool)
            throw new UserDuplicateException("이메일이 존재합니다"); //이메일 존재 예외 발생

        if(!userRegDto.getPassword().equals(userRegDto.getPasswordCheck())){ //비밀번호 일치여부 확인
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다"); //비밀번호 불일치 예외 발생
        }
        userRegDto.setPassword(passwordEncoder.encode(userRegDto.getPassword()));

        if(userRepository.existsByNickname(userRegDto.getNickname())){ // 닉네임 중복 확인
            throw new NickNameDuplicateException("닉네임이 존재합니다");
        };

        BeanUtils.copyProperties(userRegDto, userEntity);
        userRepository.save(userEntity);
    }
    // 내 정보 조회(마이페이지)
    public UserDto getMyInfo(){
        String email = CurrentUserUtil.getCurrentUserEmail();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("사용자가 없습니다"));

        return new UserDto(userEntity);
    }
    // 이미지 변경
   public void updateProfileImage(MultipartFile multipartFile, String email){
       UserEntity userEntity = userRepository.findByEmail(email)
               .orElseThrow(() -> new UserNotFoundException("사용자가 없습니다"));

       if (multipartFile.isEmpty()) {
           throw new FileEmptyException("파일 없음");
       }

       String changeFileName = userFileService.saveFile(multipartFile);

       // 기존 파일 삭제
       if (userEntity.getProfileImage() != null &&
               !userEntity.getProfileImage().equals("default.png")) {
           userFileService.deleteFile(userEntity.getProfileImage());
       }

       userEntity.setProfileImage(changeFileName);

   }

   // 기본 이미지로 변경
    public void resetProfileImage(String email) {
        UserEntity userEntity= userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("사용자가 없습니다"));

        // 기존 이미지 삭제
        if (userEntity.getProfileImage() != null &&
                !userEntity.getProfileImage().equals("default.png")) {
            userFileService.deleteFile(userEntity.getProfileImage());
        }
        userEntity.setProfileImage("default.png");
    }
    // 회원 탈퇴(soft delete)
    public void deleteUser(String email) {

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        // 이미지 삭제
        if (userEntity.getProfileImage() != null &&
                !userEntity.getProfileImage().equals("default.png")) {
            userFileService.deleteFile(userEntity.getProfileImage());
        }


        userEntity.setDeleted(true);
    }

}
