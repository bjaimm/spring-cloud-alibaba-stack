package com.example.herosoft.springclouddemo.security.controller;

import com.example.herosoft.springclouddemo.security.utils.JWTUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @GetMapping("/check/v1")
    public boolean checkJWT(@RequestParam String jwt){
        String authorization;

        if (StringUtils.isEmpty(jwt)){
            return false;
        }
        else{
            authorization=jwt.replace(JWTUtils.TOKEN_PREFIX,"");
        }
        return JWTUtils.check(authorization);
    }

}
