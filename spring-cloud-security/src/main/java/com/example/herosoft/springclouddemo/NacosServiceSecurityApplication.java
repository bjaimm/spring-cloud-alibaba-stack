package com.example.herosoft.springclouddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosServiceSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosServiceSecurityApplication.class, args);
    }

}
