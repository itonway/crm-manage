package com.itontheway.manage.util;


import java.math.BigDecimal;

/**
 * @Author xiegl
 * @Date 2020-7-28 10:21
 * @Desc
 **/
public class MapUtils {

    public static void main(String[] args){

        BigDecimal a = new BigDecimal(0);
        for(int i = 0; i<3; i++){
            BigDecimal b = new BigDecimal(i);
            a = a.add(b);
        }
        System.out.println(a);





    }


}
