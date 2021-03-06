package com.example.herosoft.springclouddemo.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosServiceSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosServiceSecurityApplication.class, args);
    }

}
