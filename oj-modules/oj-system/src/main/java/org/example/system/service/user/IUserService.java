package org.example.system.service.user;

import org.example.system.domain.user.dto.UserDTO;
import org.example.system.domain.user.dto.UserQueryDTO;
import org.example.system.domain.user.vo.UserVO;

import java.util.List;

public interface IUserService {

    List<UserVO> list(UserQueryDTO userQueryDTO);

    int updateStatus(UserDTO userDTO);
}
