package com.product.config; // 패키지 경로는 실제 패키지에 맞게 수정

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 경로 설정
        registry.addResourceHandler("/product/file/**")
                .addResourceLocations("file:///C:/Users/이재훈/IdeaProjects/product/product/src/main/webapp/product/file/");
    }
}
