package com.itontheway.manage.util;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author xiegl
 * @Date 2022-6-7 18:16
 * @Desc
 **/
public class StringConvert {

    public static void main(String[] args) {
        String message = "基本匹配：{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}{12}{13}{14}{15}{16}";
        Object[] array = new Object[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q" };
        String value = MessageFormat.format(message, array);
        System.out.println(value);


        String s = "编辑场景名称由{0}改为{1}，所属部门由{2}改为{3}";
        List<String> list = new LinkedList<>();
        list.add("A");
        list.add("A1");
        list.add("B");
        list.add("B1");

        MessageFormat mf = new MessageFormat(s);
        String format = mf.format(list.toArray());
        System.out.println(format);


        Object[] objects = {"A","A1","B","B1"};
        String result = mf.format(objects);
        System.out.println(result);

    }
}
