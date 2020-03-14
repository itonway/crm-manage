//package com.itontheway.manage.interceptor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Enumeration;
//
///**
// * @author: 公众号 itontheway
// * @description: 可以拿到原始的http请求
// * @date 2020/3/12 14:29
// */
//@Slf4j
//@Component
////@WebFilter(filterName = "WebMvcFilter",urlPatterns = "/*")
//public class WebMvcFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("web filter init 启动项目时: ...");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String s1 = request.getRequestURL().toString();
//        log.info("web filter doFilter 调用方法时 路径 {} .",s1);
//        Enumeration<String> parameterNames = request.getParameterNames();
//        while (parameterNames.hasMoreElements()){
//            String s = parameterNames.nextElement();
//            String parameter = request.getParameter(s);
//            log.info("web filter doFilter 调用方法时 参数名：{} , 参数值：{} .", s,parameter);
//        }
//        filterChain.doFilter(servletRequest,servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        log.info("web filter destroy 停止项目时 ...");
//    }
//}
