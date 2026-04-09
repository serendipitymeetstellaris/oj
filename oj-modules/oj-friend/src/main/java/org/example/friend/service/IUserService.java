package org.example.friend.service;

import org.example.common.core.domain.R;
import org.example.common.core.domain.vo.LoginUserVO;
import org.example.friend.domain.dto.UserDTO;

public interface IUserService {

    boolean sendCode(UserDTO userDTO);

    String codeLogin(String phone, String code);

    boolean logout(String token);

    R<LoginUserVO> info(String token);
}
