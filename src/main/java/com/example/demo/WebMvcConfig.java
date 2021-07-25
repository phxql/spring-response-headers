package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebMvcConfig implements WebMvcConfigurer {
    private final TestInterceptor testInterceptor;

    WebMvcConfig(TestInterceptor testInterceptor) {
        this.testInterceptor = testInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testInterceptor);
    }
}
