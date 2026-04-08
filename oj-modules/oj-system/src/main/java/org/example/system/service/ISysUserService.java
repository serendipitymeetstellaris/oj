package org.example.system.service;


import org.example.common.core.domain.R;

public interface ISysUserService {

    R<String> login(String userAccount, String password);
}
