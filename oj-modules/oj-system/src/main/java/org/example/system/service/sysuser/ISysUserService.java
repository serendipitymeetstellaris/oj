package org.example.system.service.sysuser;


import org.example.common.core.domain.R;
import org.example.common.core.domain.vo.LoginUserVO;
import org.example.system.domain.sysuser.dto.SysUserSaveDTO;

public interface ISysUserService {

    R<String> login(String userAccount, String password);

    int add(SysUserSaveDTO sysUserSaveDTO);

    R<LoginUserVO> info(String token);

    boolean logout(String token);
}
