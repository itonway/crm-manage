package com.itontheway.manage.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: 公众号 itontheway
 * @description: 加载定义的拦截器
 * @date 2020/3/12 14:44
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private WebHandlerInterceptor webHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(webHandlerInterceptor);
    }
}
