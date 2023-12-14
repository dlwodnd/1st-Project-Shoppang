package com.project.test.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
@Schema(name = "성공/실패 확인")
public class ResVo {
    @Schema(description = "성공 = 1, 실패 = 0")
    private int result;
}
