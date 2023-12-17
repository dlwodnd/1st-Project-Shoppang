package com.project.test.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(hidden = true)
public class UserInfoEntity {
    private String uid;
    private String nm;
}
