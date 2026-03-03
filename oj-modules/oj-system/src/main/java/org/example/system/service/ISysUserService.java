package org.example.system.service;


import org.example.common.core.domain.R;

public interface ISysUserService {

    R<Void> login(String userAccount, String password);
}
