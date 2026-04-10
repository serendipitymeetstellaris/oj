package org.example.judge.domain;

import lombok.Data;

@Data
public class CompileResult {

    private boolean compiled;

    private String exeMessage;
}
