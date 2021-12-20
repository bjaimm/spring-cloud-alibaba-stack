package com.example.herosoft.springclouddemo.redis.controller;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import com.example.herosoft.springclouddemo.common.domain.model.LikedCountDTO;
import com.example.herosoft.springclouddemo.redis.service.ProductService;
import com.example.herosoft.springclouddemo.redis.service.UserLikeRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private UserLikeRedisService userLikeRedisService;

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

    //如果不用@RequestParam显示接收URL中的参数，Spring会自动用param去匹配URL中的同名参数进行接收
    @RequestMapping("/testParam")
    public String testParm(String param){
        return param;
    }

    @RequestMapping(value = "/appraise")
    public String testAppraise(@RequestParam(name = "likeUserId") String likeUserId, @RequestParam(name = "likePostUserId") String likePostUserId){
          userLikeRedisService.saveLikedToRedis(likeUserId,likePostUserId);
          userLikeRedisService.incrementLikedCount(likeUserId);
          List<LikedCountDTO> list = userLikeRedisService.getLikedCountDTOFromRedis();

          LikedCountDTO likedCountDTO = list.stream().filter(item -> item.getLikedUserId().equals(likeUserId))
                  .findAny().orElse(null);
          return "User "+likedCountDTO.getLikedUserId()+ " currently has " +likedCountDTO.getLikedCount()+" appraisals";
    }

    @RequestMapping(value = "/unappraise")
    public String testUnAppraise(@RequestParam(name = "likeUserId") String likeUserId, @RequestParam(name = "likePostUserId") String likePostUserId){
        userLikeRedisService.unlikdedFromRedis(likeUserId,likePostUserId);
        userLikeRedisService.decrementLikedCount(likeUserId);

        List<LikedCountDTO> list = userLikeRedisService.getLikedCountDTOFromRedis();

        LikedCountDTO likedCountDTO = list.stream().filter(item -> item.getLikedUserId()==likeUserId)
                .findAny().orElse(null);
        return "User "+likedCountDTO.getLikedUserId()+ " currently has " +likedCountDTO.getLikedCount()+" appraisals";
    }
}
