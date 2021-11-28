package com.example.herosoft.springclouddemo.security.controller;

import com.sun.jmx.snmp.SnmpSecurityParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/auth/hello/v1")
    public String hello(){
        log.info("authentication : {}", SecurityContextHolder.getContext().getAuthentication());
        return "Hello and congratulation, you have successfully accessed inside.";
    }
}
