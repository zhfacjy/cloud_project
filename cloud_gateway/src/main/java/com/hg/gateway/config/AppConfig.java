package com.hg.gateway.config;

import com.hg.common.gateway.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "JwtUtil")
    public JwtUtil getJwtUtil() {
        return new JwtUtil();
    }

}
