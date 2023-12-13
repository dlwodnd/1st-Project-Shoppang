package com.project.test.user.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "로그인 필요 정보")
public class UserSigninDto {
    @Schema(description = "유저 아이디")
    private String uid;
    @Schema(description = "유저 비밀번호")
    private String upw;
}
