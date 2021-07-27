package com.itontheway.manage.util;

/**
 * @Author: xiegl
 * @desc
 * @Date 2021-3-11 13:46
 */
public class TestUtils {
    public static void main(String[] args) {
        String s = "(2019-2020-2)-101161009B-2001022-1";
        String[] split = s.split("-");
        for (String s1 : split) {
            System.out.println(s1);
        }
        System.out.println(split[4]);
    }
}

