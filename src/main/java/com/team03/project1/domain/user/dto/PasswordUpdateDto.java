package com.team03.project1.domain.user.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordUpdateDto {
    private String currentPassword;
    private String newPassword;
}
