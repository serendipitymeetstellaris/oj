package org.example.common.security.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {

    private Integer identity; //1.表示普通用户  2.表示管理员用户
}
