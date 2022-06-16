package com.itontheway.manage.config;

import cn.hutool.json.JSONUtil;
import com.itontheway.manage.annotation.IgnorIntercept;
import com.itontheway.manage.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author: 公众号 itontheway
 * @description: 实现ResponseBodyAdvice接口，可以对返回值在输出之前进行修改
 * @date 2020/3/10 16:36
 */
@Slf4j
@RestControllerAdvice(basePackages = {"com.itontheway.manage.controller"})
public class GlobalResultConfig implements ResponseBodyAdvice<Object>{
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/10 16:52
     * @Desc 判断哪些需要拦截
     * @Param [methodParameter, aClass]
     * @Return boolean
     **/
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 检查注解是否存在，存在则忽略拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnorIntercept.class)) {
            return false;
        }
        if (methodParameter.getMethod().isAnnotationPresent(IgnorIntercept.class)) {
            return false;
        }
        return true;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/10 16:52
     * @Desc 对返回值在输出之前进行修改，从而实现返回统一的接口数据。
     * @Param [o, methodParameter, mediaType, aClass, serverHttpRequest, serverHttpResponse]
     * @Return java.lang.Object
     **/
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 如果返回值为空，则直接封装成功的参数数，返回
        if (null == o) {
            return Result.success("操作成功");
        }
        // 判断是Result子类或其本身就返回Object o本身，因为有可能是接口返回时创建了Result,这里避免再次封装
        if (o instanceof Result) {
            return (Result<Object>) o;
        }
        // 如果返回值类型为string，则用josn转换处理
        if (o instanceof String) {
            return JSONUtil.toJsonStr(Result.success("操作成功",o));
        }
        return Result.success("操作成功",o);
    }
}
