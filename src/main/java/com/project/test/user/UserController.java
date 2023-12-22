package com.project.test.user;

import com.project.test.common.ResVo;
import com.project.test.user.models.dto.UserInsDto;
import com.project.test.user.models.dto.UserSigninDto;
import com.project.test.user.models.vo.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService USER_SERVICE;

    @PostMapping("/signup")
    @Operation(summary = "유저 회원가입", description = "유저 회원가입.")
    public ResVo userSignup(@RequestBody UserInsDto dto) {
        return USER_SERVICE.userSignup(dto);
    }

    @PostMapping("/signin")
    @Operation(summary = "유저 로그인", description = "유저 로그인.")
    public ResVo userSignin(@RequestBody UserSigninDto dto) {
        return USER_SERVICE.userSignin(dto);
    }



}
