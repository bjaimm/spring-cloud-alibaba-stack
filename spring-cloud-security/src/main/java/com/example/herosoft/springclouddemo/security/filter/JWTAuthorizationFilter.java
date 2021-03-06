package com.example.herosoft.springclouddemo.security.filter;

import com.example.herosoft.springclouddemo.security.domain.dto.CustomUserDetails;
import com.example.herosoft.springclouddemo.security.utils.JWTUtils;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private static final Set<String> WHITE_LIST= Stream.of("/v1/auth/register","/v1/auth/check").collect(Collectors.toSet());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("authorization filter doFilterInternal");
        final String authorization = request.getHeader(JWTUtils.TOKEN_HEADER);
        log.info("raw access-token:{}",authorization);

        if(StringUtils.isNullOrEmpty(authorization)){
            if(WHITE_LIST.contains(request.getRequestURI())){
                filterChain.doFilter(request,response);
            }
            else{
                response.getWriter().write("未经授权的访问！");
            }
            return;
        }

        final String jsonWebToken = authorization.replace(JWTUtils.TOKEN_PREFIX,"");

        CustomUserDetails customUserDetails = JWTUtils.userDetails(jsonWebToken);

        log.info(String.valueOf(customUserDetails.getAuthorities().size()));
        customUserDetails.getAuthorities().forEach(item -> log.info(item.getAuthority()));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        customUserDetails,
                        customUserDetails.getPassword(),
                        customUserDetails.getAuthorities()
                )
        );
        filterChain.doFilter(request,response);
    }
}
