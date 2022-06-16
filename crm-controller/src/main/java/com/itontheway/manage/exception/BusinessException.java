package com.itontheway.manage.exception;

/**
 * @Author xiegl
 * @Date 2022-6-16 11:29
 * @Desc
 **/
public class BusinessException extends BasicException{
    private static final long serialVersionUID = 1L;

    public BusinessException(int code, String message) {
        super(code, message);
    }
}
