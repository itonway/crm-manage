package com.itontheway.manage.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: xiegl
 * @desc
 * @Date 2021-3-9 9:23
 */
public class EncryptionUtils {
    public static String getMd5(String rawStr) {
        return DigestUtils.md5Hex(null == rawStr ? StringUtils.EMPTY : rawStr);
    }

    public static void main(String[] args) {
        //dolphinscheduler123 7ad2410b2f4c074479a8937a28a22b8f
        String password = "admin123";
        String md5 = EncryptionUtils.getMd5(password);
        System.out.println(md5);
    }





}
