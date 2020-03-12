package com.itontheway.manage.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 公众号 itontheway
 * @description: 可以拿到请求的控制器和方法，却拿不到请求方法的参数。
 * @date 2020/3/12 14:40
 */
@Slf4j
@Component
public class WebHandlerInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("WebHandlerInterceptor preHandle 在请求处理之前进行调用（Controller方法调用之前）");
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("WebHandlerInterceptor preHandle controller 名称：{}", handlerMethod.getBean().getClass().getName());
            log.info("WebHandlerInterceptor preHandle controller 方法：{}", handlerMethod.getMethod());
            log.info("WebHandlerInterceptor preHandle url 路径： {}", request.getRequestURL());
        }
        // 可根据情况，决定返回 false 或 true，
        // 需要返回true，否则请求不会被控制器处理
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("WebHandlerInterceptor postHandle 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后），如果异常发生，则该方法不会被调用");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("WebHandlerInterceptor afterCompletion 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
