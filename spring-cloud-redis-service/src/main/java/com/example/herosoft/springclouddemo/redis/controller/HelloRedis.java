package com.example.herosoft.springclouddemo.redis.controller;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import com.example.herosoft.springclouddemo.redis.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping( "/redis")
public class HelloRedis {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProductService productService;

    @RequestMapping("/helloRedis")
    public String helloRedis() throws InterruptedException {
        ShopProduct product = new ShopProduct();

        if(redisTemplate.opsForValue().get("product")==null) {
            log.info("Redis缓存没有发现数据，开始从数据库读取。。。");

            redisTemplate.opsForValue().set("product", productService.findProductById(1));

            Thread.sleep(5000);
        }
        else {
            log.info("Redis缓存有数据，则直接读取。。。");
            product = (ShopProduct) redisTemplate.opsForValue().get("product");
        }

        return "Product in redis is "+product.getProductName();

    }
}
