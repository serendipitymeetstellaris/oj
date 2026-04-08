package org.example.system.service;


import org.example.common.core.domain.R;
import org.example.system.domain.dto.SysUserSaveDTO;

public interface ISysUserService {

    R<String> login(String userAccount, String password);

    int add(SysUserSaveDTO sysUserSaveDTO);
}
