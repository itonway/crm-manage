package com.itontheway.manage.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author xiegl
 * @Date 2022-6-16 11:29
 * @Desc 异常实体类
 **/
@Data
public class ExceptionEntity {

    private String message;

    private int  code;

    private String error;

    private String path;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date timestamp  = new Date();
}
