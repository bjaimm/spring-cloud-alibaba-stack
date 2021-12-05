package com.example.herosoft.springclouddemo.common.domain.model;

import lombok.Data;

@Data
public class Result {
    private boolean flag;
    private String code;
    private String message;
    private Object data;

    public Result(boolean flag, String code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
