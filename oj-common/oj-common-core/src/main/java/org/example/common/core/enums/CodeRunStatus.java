package org.example.common.core.enums;

import lombok.Getter;

@Getter
public enum CodeRunStatus {

    RUNNING(1, "运行中"),

    SUCCEED(2, "运行成功"),

    FAILED(3, "运行失败"),

    NOT_ALL_PASSED(4, "未通过所有用例"),

    UNKNOWN_FAILED(5, "未知异常，请您稍后重试"),

    COMPILE_FAILED(6, "编译失败");

    private Integer value;

    private String msg;

    CodeRunStatus(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
