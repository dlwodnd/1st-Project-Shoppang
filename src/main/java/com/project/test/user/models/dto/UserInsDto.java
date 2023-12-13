package com.project.test.user.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Schema(name = "유저 회원가입 필요 정보")
public class UserInsDto {
    @Schema(description = "유저 아이디")
    private String uid;
    @Schema(description = "유저 비밀번호")
    private String upw;
    @Schema(description = "유저 이름")
    private String nm;
}
