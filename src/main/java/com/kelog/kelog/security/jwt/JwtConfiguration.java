package com.kelog.kelog.security.jwt;
import com.kelog.kelog.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final String secretKey;
    @Autowired
    private final TokenProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

// JWT 필터 설정 메소드
    @Override
    public void configure(HttpSecurity httpSecurity) {
//        필터 객체 생성
        JwtFilter customJwtFilter = new JwtFilter(secretKey , tokenProvider, userDetailsService);
//      usernamepassword 필터보다 먼저 실행.
        httpSecurity.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
