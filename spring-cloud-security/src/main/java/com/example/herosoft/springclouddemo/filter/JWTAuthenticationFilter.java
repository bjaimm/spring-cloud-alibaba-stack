package com.example.herosoft.springclouddemo.filter;

import com.alibaba.fastjson.JSON;
import com.example.herosoft.springclouddemo.domain.dto.CustomUserDetails;
import com.example.herosoft.springclouddemo.utils.JWTUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
        super.setFilterProcessesUrl("/auth/login");
        super.setUsernameParameter("name");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        log.info("getInputStream: "+request.getInputStream().toString());

        //User user = JSON.parseObject(request.getInputStream(), StandardCharsets.UTF_8, User.class);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                request.getParameter("username"), request.getParameter("password"));

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return super.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("authentication filter successful authentication:{}", authResult);

        User user = (User) authResult.getPrincipal();
        CustomUserDetails customUserDetails = new CustomUserDetails(
                user.getUsername(),
                "",
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getAuthorities()
        );
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
