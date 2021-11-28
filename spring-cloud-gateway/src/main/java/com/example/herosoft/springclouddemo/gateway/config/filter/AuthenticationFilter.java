package com.example.herosoft.springclouddemo.gateway.config.filter;

import com.example.herosoft.springclouddemo.gateway.config.JsonResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class AuthenticationFilter {
    private static final String JWT_TOKEN="Authorization";

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @Order
    public GlobalFilter authJWT(){
        return (exchange, chain) -> {

            log.info("GlobalFilter is starting checking JWT...");

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            //判断是否需要过滤
            String path = request.getURI().getPath();
            List<String> pages= Arrays.asList("/auth/login/","/auth/refresh/","/auth/check/");

            for (String page : pages) {
                if (path.startsWith(page)) {
                    //直接通过，传到下一级
                    return chain.filter(exchange);
                }
            }

            //判断是否存在JWT
            String jwt="";
            List<String> headers=request.getHeaders().get(JWT_TOKEN);
            if(null!=headers && headers.size()>0){
                jwt= headers.get(0);
            }
            if(StringUtils.isEmpty(jwt)){
                MultiValueMap<String, HttpCookie> cookies = request.getCookies();

                if(null!=cookies && cookies.size()>0){
                    List<HttpCookie> cookie = cookies.get(JWT_TOKEN);
                    if(null!=cookie && cookie.size()>0) {
                        HttpCookie ck = cookie.get(0);
                        jwt=ck.getValue();
                    }
                }
            }

            if(StringUtils.isEmpty(jwt)){
                return error(response, JsonResponseUtils.AUTH_UNLOGIN_ERROR);
            }

            //通过远程调用鉴权服务判断JWT是否合法
            String json="";
            try{
                Boolean ret = restTemplate.getForObject("http://spring-cloud-security/auth/check/v1?jwt="+jwt,Boolean.class);

                if(!ret){
                    //返回错误
                    return error(response,JsonResponseUtils.AUTH_EXP_ERROR);
                }
                return chain.filter(exchange);
            }catch (Exception e){
                e.printStackTrace();
                return error(response,JsonResponseUtils.AUTH_EXP_ERROR);
            }

            //将登陆信息保存到下一级
            /*ServerHttpRequest newrequest = request.mutate().header("auth",json).build();
            ServerWebExchange newexchange = exchange.mutate().request(newrequest).build();
            return chain.filter(newexchange);*/
        };
    }

    private Mono<Void> error(ServerHttpResponse response, String authUnloginError) {
        //返回错误
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = response.bufferFactory().wrap(authUnloginError.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));

    }
}
