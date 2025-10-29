package com.ljjmk94.japanese_dictionary_n1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 管理前端來源，支援多個前端來源
    @Value("${frontend.urls}")
    private String[] frontendUrls;

    // 封裝成常量 + 方法，提高可讀性
    private static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE", "OPTIONS"};
    private static final String[] ALLOWED_HEADERS = {"*"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // 配合你的 Controller 中的路徑
                .allowedOrigins(frontendUrls) // 允許前端的來源
                .allowedMethods(ALLOWED_METHODS)
                .allowedHeaders(ALLOWED_HEADERS)
                .allowCredentials(true); // 如果你用 cookies，就打開；否則可以拿掉
    }
    
}
