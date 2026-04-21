package com.team03.project1.controller.user;

import com.team03.project1.config.JwtUtil;
import com.team03.project1.domain.user.dto.LoginDto;
import com.team03.project1.domain.user.service.AuthService;
import com.team03.project1.exception.InvalidLoginException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    //로그인
    @PostMapping("/login")
    @Operation(
            summary = "로그인",
            description = "username과 password 인증"
    )
    @ApiResponses({ //API 응답(상태코드, 설명)을 Swagger에 정의
            //성공
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content( //요청/응답 바디의 내용을 설명
                            mediaType = "application/json", // 응답형식 지정
                            schema = @Schema(implementation = Integer.class, example = "0")
                    )
            ),
            //사용자 없음(실패)
            @ApiResponse(responseCode = "401", description = "아이디 또는 비번 틀림",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class, example = "1")
                    )
            )
    })
    public ResponseEntity<Map<String, String>> login(@ParameterObject @ModelAttribute LoginDto loginDto){
        System.out.println("loginDto => " + loginDto);
        //로그인 인증(토큰), Spring Security용 "로그인 요청 객체"
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                );
        Authentication authentication = null;
        // 내부에서 spring security가 검증해준다(DB조회+아이디/비밀번호 검증)
        try {
            authentication = authenticationManager.authenticate(token);
        } catch (BadCredentialsException e){
            throw new InvalidLoginException("아이디 또는 비밀번호가 일치하지 않습니다");
        }

        System.out.println("인증된 사용자 정보 : "+authentication.getPrincipal());
        //로그인(인증) 성공시 사용자 정보 가져오기
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        System.out.println("userDetails.getAuthorities : "+userDetails.getAuthorities());
        String role = userDetails.getAuthorities().iterator().next().getAuthority(); //role 꺼내오기
        String resultToken = jwtUtil.generateToken(userDetails.getUsername(), role);
        return ResponseEntity.ok(Collections.singletonMap("token", resultToken));

    }
}
