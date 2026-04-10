package org.example.friend.domain.user.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserQuestionResultVO {
    //是够通过标识
    private Integer result; // 0  未通过  1 通过

    private String errorMsg; //异常信息

    private List<UserExeResult> userExeResultList;
}
