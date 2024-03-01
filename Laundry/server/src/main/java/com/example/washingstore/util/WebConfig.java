package com.example.washingstore.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//全局配置：跨域请求
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    public void addCorsMappings(CorsRegistry registry){
        /**
         * 1.与访问路径
         * 2.请求来源
         * 3.方法
         * 4.允许携带
         * 5.响应时间（最大）
         */
        registry.addMapping("/**")
                .allowedOrigins("Http://LocalHost:8080","null")
                .allowedMethods("GET","POST","PUT","OPTION","DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
