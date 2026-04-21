package com.team03.project1.domain.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegDto {
    @Email(message = "이메일 형식이 올바르지 않습니다") //이메일 형식 체크
    @NotBlank(message = "이메일은 필수입니다") // 필수 체크
    private String email; //id(이메일)
    @NotBlank(message = "비밀번호는 필수입니다") // 필수 체크
    private String password;
    @NotBlank(message = "비밀번호 확인은 필수입니다") // 필수 체크
    private String passwordCheck;
    @NotBlank(message = "닉네임은 필수입니다") // 필수 체크
    private String nickname;
}
