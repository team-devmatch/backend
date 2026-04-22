package com.team03.project1.controller.user;

import com.team03.project1.domain.user.dto.UserDto;
import com.team03.project1.domain.user.dto.UserRegDto;
import com.team03.project1.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //@GetMapping
    public String test() {
        return "서버 정상 작동!";
    }

    //회원가입
    @PostMapping(value = "register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // swagger에서 file형식으로 보여주기위함,파일이 포함된 요청(multipart/form-data)을 받기 위함
    @Operation(
            summary = "회원 가입",
            description = "사용자를 추가 합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "가입 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class, example = "가입 성공")
                    )
            ),
            @ApiResponse(responseCode = "400", description = "가입 실패",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value="회원가입 실패")
                    )
            )
    })
    public ResponseEntity<String> register(
            @RequestParam(value = "file", required = false ) MultipartFile multipartFile, //required = false : 값이 들어오지 않으면 해당하는 값은 null로 처리한다.
            @Valid @ParameterObject @ModelAttribute UserRegDto userRegDto){
        System.out.println("컨트롤러 진입");
        System.out.println("multipartFile : "+ multipartFile);

        userService.insert(userRegDto, multipartFile);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 성공");

    }
    // 내 정보 조회(마이페이지)
    @GetMapping("/mypage")
    @SecurityRequirement(name="JWT")
    @Operation(
            summary = "내 정보 조회",
            description = "사용자 정보 조회"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "사용자 없음",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value="null")
                    )
            )
    })
    public ResponseEntity<UserDto> getMyInfo(){
        UserDto userDto = null;

        userDto = userService.getMyInfo();

        return ResponseEntity.ok(userDto);

    }
/*
    @PutMapping("/mypage/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name="JWT")
    @Operation(
            summary = "이미지 변경",
            description = "이미지를 변경합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "변경 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Void.class) // void.class: void를 클래스 형태로 표현, 응답 바디가 없다
                    )
            ),
            @ApiResponse(responseCode = "404", description = "변경 실패",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Void.class)
                    )
            )
    })
    public ResponseEntity<Void> update(@RequestParam(value="file", required = false) MultipartFile multipartFile, Authentication authentication){
        UserService.modify(multipartFile, authentication.getName());

        return ResponseEntity.ok().build();
    }
 */
}
