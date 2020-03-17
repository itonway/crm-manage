package com.itontheway.manage.common;

import sun.misc.BASE64Encoder;

import java.net.URLEncoder;

/**
 * @Author 公众号 itontheway
 * @Date 2020/3/17 19:56
 * @Desc 文件处理类
 **/
public class FileHandlerUtils {
    private static final String FIREFOX = "Firefox";
    private static final String SAFARI = "Safari";
    private static final String EDGE = "Edge";

    private static final String UTF8 = "utf-8";


    public static String getFileName(String fileName, String agent) {
        try {
            if (agent != null && agent.contains ( FIREFOX )) {
                String realName = "" + new BASE64Encoder ().encode ( fileName.getBytes ( UTF8 ) ) + "?=";
                return realName.replaceAll ( "\r\n", "" );
            } else if (agent != null && agent.contains ( SAFARI ) && !agent.contains ( EDGE )) {
                return new String ( fileName.getBytes ( UTF8 ), "iso8859-1" );
            } else {
                String realName = URLEncoder.encode ( fileName, UTF8 );
                return realName.replace ( "+", " " );
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return fileName;

    }


}
