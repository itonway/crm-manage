package com.itontheway.manage.controller;

import com.itontheway.manage.annotation.IgnorIntercept;
import com.itontheway.manage.entity.User;
import com.itontheway.manage.exception.CustomizeExceptionDemo;
import com.itontheway.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController{

    @Autowired
    IUserService userService;
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 12:35 
     * @Desc //TODO
     * @Param [user]
     * @Return java.util.List<com.itontheway.manage.entity.User>
     **/
    @RequestMapping(value = "findUserList",method = RequestMethod.GET)
    public List<User> findUserList(User user){
        List<User> userList = userService.findListByEntity(user);
        return userList;
    }
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/10 16:50
     * @Desc 根据 ID 查询
     * @Param [id]
     * @Return com.itontheway.manage.entity.User
     **/
    @IgnorIntercept
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public User findById(Long id){
        return userService.findById(id);
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/10 17:31
     * @Desc //异常处理
     * @Param []
     * @Return java.lang.String
     **/
    @RequestMapping(value = "exceptionHandler",method = RequestMethod.GET)
    public String exceptionHandler() {
        Integer x = 1 / 0;
        return x.toString();
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/10 17:31
     * @Desc //自定义异常
     * @Param []
     * @Return java.lang.String
     **/
    @RequestMapping(value = "customizeExceptionHandler",method = RequestMethod.GET)
    public String customizeExceptionHandler() throws CustomizeExceptionDemo {
        throw new CustomizeExceptionDemo("0","自定义异常信息");
    }

}
