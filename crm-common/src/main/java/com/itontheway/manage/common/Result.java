package com.itontheway.manage.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 21:38
 */
@Data
public class Result<T> implements Serializable {
    //消息
    private String msg;
    //0失败 1成功
    private Integer code;
    //返回的数据
    private T data;

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(1);
        result.setMsg("SUCCESS");
        result.setData(data);
        return result;
    }

    public static Result fail(String msg) {
        return fail(msg,null);
    }

    public static Result fail(String msg, Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
