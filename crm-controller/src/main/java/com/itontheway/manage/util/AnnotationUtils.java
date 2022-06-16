//package com.itontheway.manage.util;
//
//import com.baomidou.mybatisplus.annotation.TableName;
//import org.reflections.Reflections;
//
//import java.util.Set;
//
///**
// * @Author: xiegl
// * @desc
// * @Date 2021-2-25 16:36
// */
//public class AnnotationUtils {
//    public static void main(String[] args) {
//        //入参 要扫描的包名
//        Reflections f = new Reflections("com.itontheway.manage.entity.vo");
//        //入参 目标注解类
//        Set<Class<?>> set = f.getTypesAnnotatedWith(TableName.class);
//        for (Class<?> aClass : set) {
//            TableName tableName = aClass.getAnnotation(TableName.class);
//            String value = tableName.value();
//            System.out.println(value);
//            Class<? extends Class> aClass1 = aClass.getClass();
//        }
//    }
//}
