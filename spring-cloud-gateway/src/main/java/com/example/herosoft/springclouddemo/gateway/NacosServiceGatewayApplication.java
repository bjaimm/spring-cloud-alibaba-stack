package com.example.herosoft.springclouddemo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosServiceGatewayApplication {
/*以下代码是实现RestTemplate+Ribbon进行负载均衡, 如果用Feign+Ribbon实现负载均衡，这里就不能申明@Loadbanced RestTemplate，
否则Feign远程方法调用抛异常*/

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(NacosServiceGatewayApplication.class, args);
    }

}
