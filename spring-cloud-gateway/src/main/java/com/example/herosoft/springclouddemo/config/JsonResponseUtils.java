package com.example.herosoft.springclouddemo.config;

public class JsonResponseUtils {
    public static final String BLOCK_FLOW_ERROR = "{\"code\": -1, \"data\": null, \"msg\": \"系统限流\"}";
    public static final String AUTH_UNLOGIN_ERROR = "{\"code\": -1, \"data\": null, \"msg\": \"未授权\"}";
    public static final String AUTH_EXP_ERROR = "{\"code\": -1, \"data\": null, \"msg\": \"授权过期\"}";
    public static final String AUTH_PARAM_ERROR = "{\"code\": -1, \"data\": null, \"msg\": \"参数异常\"}";
}
