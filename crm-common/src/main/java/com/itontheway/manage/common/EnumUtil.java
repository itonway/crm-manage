package com.itontheway.manage.common;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/14 10:42
 */
public enum  EnumUtil {
    SUCCESS("1","成功"),
    FAILED("0","失败");

    EnumUtil(String msg, String code){
        this.code = code;
        this.msg = msg;
    }

    EnumUtil(){
        this.code = "1";
        this.msg = "成功";
    }

    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
