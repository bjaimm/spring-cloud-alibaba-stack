package com.example.herosoft.springclouddemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.herosoft.springclouddemo.domain.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class JWTUtils {

    public static final String TOKEN_HEADER="Authentication";
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String SECRET= "ajfalsfjalksfjasklfjakdsjflkdsajfdsalkjfdsalkjflkdsajfldsajflkdsajfkdsajfkdsajfkwuewruewourwuroewuewou";
    public static final String ISSUER="Herosoft";

    private JWTUtils(){}

    public static String create(String username, boolean rememberMe, CustomUserDetails userDetails){
        return Jwts.builder()
                .setClaims(JSON.parseObject(JSON.toJSONString(userDetails)))
                .setSubject(username)
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setIssuer(ISSUER)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS512)
                .serializeToJsonWith(map -> JSON.toJSONBytes(map))
                .compact();

    }
    public static Claims claims(String jwt){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .deserializeJsonWith(bytes -> JSONObject.parseObject(new String(bytes),new TypeReference<Map<String,Object>>(){}))
                .build()
                .parseClaimsJws(jwt)
                .getBody();

    }
    public static String subject(String jwt){
        return claims(jwt).getSubject();
    }
    public static CustomUserDetails userDetails(String jwt){

        List<GrantedAuthority> authorityList = new ArrayList<>();

        Claims claims = claims(jwt);
        String jsonString = JSON.toJSONString(claims);

        JSONArray jsonArray = JSONObject.parseObject(JSON.toJSONString(claims)).getJSONArray("authorities");
        for(Object obj : jsonArray){
            String authority = (String) JSONObject.parseObject(obj.toString()).get("authority");

            authorityList.add(new SimpleGrantedAuthority(authority));
        }

        CustomUserDetails customUserDetails =JSON.parseObject(jsonString,CustomUserDetails.class);
        customUserDetails.setAuthorities(authorityList);

        return customUserDetails;

    }

}
