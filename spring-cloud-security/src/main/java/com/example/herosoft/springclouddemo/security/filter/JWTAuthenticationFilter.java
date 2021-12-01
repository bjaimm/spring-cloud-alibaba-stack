package com.example.herosoft.springclouddemo.security.filter;

import com.alibaba.fastjson.JSON;
import com.example.herosoft.springclouddemo.security.domain.dto.CustomUserDetails;
import com.example.herosoft.springclouddemo.security.utils.JWTUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Slf4j
@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JWTAuthenticationFilter() {
        super.setFilterProcessesUrl("/v1/auth/login");
        //super.setUsernameParameter("name");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        log.info("getInputStream: "+request.getInputStream().toString());

        LoginUser loginUser = JSON.parseObject(request.getInputStream(), StandardCharsets.UTF_8, LoginUser.class);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(), loginUser.getPassword());

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return super.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("authentication filter successful authentication:{}", authResult);

        CustomUserDetails customUserDetails =(CustomUserDetails) authResult.getPrincipal();

        //清除password信息后，再生成JWT
        customUserDetails.setPassword("");

        response.setHeader("access-token", JWTUtils.TOKEN_PREFIX + JWTUtils.create(customUserDetails.getUsername(), false, customUserDetails));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("authentication filter failure authentication:{}", failed.getMessage());
        response.getWriter().write("authentication filter failed, reasons:" + failed.getMessage());
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
