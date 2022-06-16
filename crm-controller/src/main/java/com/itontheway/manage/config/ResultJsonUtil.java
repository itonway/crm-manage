package com.itontheway.manage.config;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author xiegl
 * @Date 2022-6-16 12:35
 * @Desc
 **/
@Data
public class ResultJsonUtil<T> {
    private int code;
    private int statusCode;
    private String msg;
    private T data;

    private static final int DEFAULT_STATUS_CODE = 0;

    /**
     * construction
     * @param code 请求状态码
     * @param statusCode 信息状态码
     * @param msg  信息
     * @param data 数据
     */
    public ResultJsonUtil(int code, int statusCode, String msg, T data) {
        this.code       = code;
        this.statusCode = statusCode;
        this.msg        = msg;
        this.data       = data;
    }

    public static String build(int code, int statusCode, String msg) {
        ResultJsonUtil<String> resultJsonUtil = new ResultJsonUtil<>(code, statusCode, msg, "");
        return resultJsonUtil.getResultJson();
    }

    public static String build(int code, String msg) {
        return ResultJsonUtil.build(code, ResultJsonUtil.DEFAULT_STATUS_CODE, msg);
    }

    public static String build(int code, int statusCode, String msg, JSONArray data) {
        ResultJsonUtil<JSONArray> resultJsonUtil = new ResultJsonUtil<>(code, statusCode, msg, data);
        return resultJsonUtil.getResultJson();
    }

    public static String build(int code, String msg, JSONArray data) {
        return ResultJsonUtil.build(code, ResultJsonUtil.DEFAULT_STATUS_CODE, msg, data);
    }


    public static String build(int code, int statusCode, String msg, Map data) {
        JSONObject jsonObjectData = JSONUtil.parseObj(JSONUtil.toJsonStr(data));
        ResultJsonUtil<JSONObject> resultJsonUtil = new ResultJsonUtil<>(code, statusCode, msg, jsonObjectData);
        return resultJsonUtil.getResultJson();
    }

    public static String build(int code, String msg, Map data) {
        return ResultJsonUtil.build(code, ResultJsonUtil.DEFAULT_STATUS_CODE, msg, data);
    }


    public static String build(int code, int statusCode, String msg, List data) {
        JSONArray jsonArrayData = JSONUtil.parseArray(JSONUtil.toJsonStr(data));
        return ResultJsonUtil.build(code, statusCode, msg, jsonArrayData);
    }

    public static String build(int code, String msg, List data) {
        return ResultJsonUtil.build(code, ResultJsonUtil.DEFAULT_STATUS_CODE, msg, data);
    }

    private String getResultJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("code", this.code);
        jsonObject.putOpt("status_code", this.statusCode);
        jsonObject.putOpt("msg", this.msg);
        jsonObject.putOpt("data", this.data);
        return JSONUtil.toJsonStr(jsonObject);
    }
}
