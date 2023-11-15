package com.catolica.sc.spaum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
                .allowedHeaders("Authorization", "Access-Control-Allow-Origin", "Accept", "Content-Type", "Origin", "Cache-Control", "x-auth-token");
    }

}
