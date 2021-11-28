package com.example.herosoft.springclouddemo.controller;

import com.example.herosoft.springclouddemo.utils.JWTUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static javafx.scene.input.KeyCode.R;

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
