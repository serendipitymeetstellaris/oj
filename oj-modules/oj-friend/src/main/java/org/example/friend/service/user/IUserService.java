package org.example.friend.service.user;

import org.example.common.core.domain.R;
import org.example.common.core.domain.vo.LoginUserVO;
import org.example.friend.domain.user.dto.UserDTO;

public interface IUserService {

    boolean sendCode(UserDTO userDTO);

    String codeLogin(String phone, String code);

    boolean logout(String token);

    R<LoginUserVO> info(String token);
}
