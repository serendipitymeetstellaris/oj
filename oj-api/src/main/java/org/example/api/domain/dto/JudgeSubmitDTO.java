package org.example.api.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JudgeSubmitDTO {

    private Long userId;

    private Long examId;

    //编程语言类型（0 java 1 C++）
    private Integer programType;

    private Long questionId;

    //题目难度
    private Integer difficulty;

    //时间限制 ms
    private Long timeLimit;

    //空间限制 kb
    private Long spaceLimit;

    //用户代码
    private String userCode;

    //题目用例
    private List<String> inputList;

    private List<String> outputList;
}
