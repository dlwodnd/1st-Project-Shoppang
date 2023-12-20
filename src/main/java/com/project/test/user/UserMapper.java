package com.project.test.user;

import com.project.test.user.models.dto.UserInsDto;
import com.project.test.user.models.dto.UserSigninDto;
import com.project.test.common.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int userSignup(UserInsDto dto);

    String checkUpw(UserSigninDto dto);

    UserInfoEntity checkUserPk(int userPk);
}
