package com.example.herosoft.springclouddemo.config;

import com.example.herosoft.springclouddemo.filter.JWTAuthenticationFilter;
import com.example.herosoft.springclouddemo.filter.JWTAuthorizationFilter;
import com.example.herosoft.springclouddemo.handler.CustomAuthenticationFailureHandler;
import com.example.herosoft.springclouddemo.handler.CustomSimpleUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private CustomSimpleUrlAuthenticationSuccessHandler customSimpleUrlAuthenticationSuccessHandler;
    private PasswordEncoder passwordEncoder;
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Autowired
    JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .withUser("root").roles("ADMIN").password(passwordEncoder().encode("root"))
                .and()
                .withUser("user").roles("User").password(passwordEncoder.encode("user"));*/
        auth.authenticationProvider(usernamePasswordAuthenticationProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests().anyRequest().authenticated()
                .and().httpBasic()
                .and().formLogin().failureHandler(customAuthenticationFailureHandler).successHandler(customSimpleUrlAuthenticationSuccessHandler)
                .and().csrf().disable();*/

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().hasAnyAuthority("ADMIN")
                .and()
                .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthorizationFilter,JWTAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setCustomAuthenticationFailureHandler(CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }
    @Autowired
    public void setCustomSimpleUrlAuthenticationSuccessHandler(CustomSimpleUrlAuthenticationSuccessHandler customSimpleUrlAuthenticationSuccessHandler) {
        this.customSimpleUrlAuthenticationSuccessHandler = customSimpleUrlAuthenticationSuccessHandler;
    }
    @Autowired
    public void setUsernamePasswordAuthenticationProvider(UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider) {
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
