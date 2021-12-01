package com.example.herosoft.springclouddemo.security.controller;

import com.example.herosoft.springclouddemo.security.domain.dto.CustomUserDetails;
import com.example.herosoft.springclouddemo.security.utils.JWTUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/auth")
public class AuthController {

    @RequestMapping(value="/check")
    public Map checkJWT(@RequestParam String jwt){
        String authorization;
        Map map = new HashMap();
        CustomUserDetails customUserDetails;

        if (StringUtils.isEmpty(jwt)){
            return map;
        }
        else{
            authorization=jwt.replace(JWTUtils.TOKEN_PREFIX,"");
        }
        customUserDetails= JWTUtils.userDetails(authorization);
        if(customUserDetails!=null){
            map.put("username",customUserDetails.getUsername());
            map.put("deptno",customUserDetails.getDeptno());
            map.put("authorities",customUserDetails.getAuthorities());
        }
        return map;
    }

}
