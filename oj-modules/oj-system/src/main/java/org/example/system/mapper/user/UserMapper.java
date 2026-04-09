package org.example.system.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.system.domain.user.User;
import org.example.system.domain.user.dto.UserQueryDTO;
import org.example.system.domain.user.vo.UserVO;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<UserVO> selectUserList(UserQueryDTO userQueryDTO);
}
