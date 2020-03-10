package com.itontheway.manage.exception;

import lombok.Data;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/10 17:58
 */
@Data
public class CustomizeExceptionDemo extends RuntimeException {
    private String code;
    private String msg;

    public CustomizeExceptionDemo(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
