package org.example.system.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {

    private int code;   //0 失败   1成功

    private String msg;  //失败的信息
}
