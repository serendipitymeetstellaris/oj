package org.example.friend.service;

import org.example.friend.domain.dto.UserDTO;

public interface IUserService {

    boolean sendCode(UserDTO userDTO);

    String codeLogin(String phone, String code);
}
