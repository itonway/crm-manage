package com.itontheway.manage.common;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 21:41
 */
public enum  Const {

    SUCCESS("0000","成功"),
    FAILED("-9999","失败");

    Const(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
