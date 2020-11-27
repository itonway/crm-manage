package com.itontheway.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: 公众号 itontheway
 * @description: 主启动类
 * @date 2020/3/7 12:21
 */
@SpringBootApplication
//dao层所在目录，不然扫描不到
@MapperScan(value = "com.itontheway.manage.dao")
//保证切面可以切入
@EnableAspectJAutoProxy
//保证定时任务执行
@EnableScheduling
public class CrmManageWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmManageWebApplication.class);

//        User user = new User();
//        user.setUserName("A");
//        user.setSex("2");
//
//        User user1 = new User();
//        user1.setUserName("A");
//        user1.setSex("1");
//
//        User user2 = new User();
//        user2.setUserName("C");
//        user2.setSex("1");
//
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
//        userList.add(user1);
//        userList.add(user2);
//
//
//        String userName = "A";
//        String[] sexArr = {"1","2"};
//        List<String> strings3 = Arrays.asList(sexArr);
//
//        List<User> collect = userList.stream().filter(user3 -> strings3.contains(user3.getSex()) && userName.equals(user3.getUserName())).collect(Collectors.toList());
//        collect.forEach(user4 -> System.out.println(user4.getUserName() + "---" + user4.getSex()));


    }
}
