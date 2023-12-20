package com.project.test.user;

import com.project.test.common.ResVo;
import com.project.test.user.models.dto.UserInsDto;
import com.project.test.user.models.dto.UserSigninDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper USER_MAPPER;

    public ResVo userSignup(UserInsDto dto) {
        int result = USER_MAPPER.userSignup(dto);
        return new ResVo(result);
    }

    public ResVo userSignin(UserSigninDto dto) {
        String userPw = USER_MAPPER.checkUpw(dto);
        if (dto.getUpw().equals(userPw)) {
            return new ResVo(1);
        }
        return new ResVo(0);
    }
}
