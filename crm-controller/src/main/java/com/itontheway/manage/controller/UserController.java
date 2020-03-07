package com.itontheway.manage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "userController")
public class UserController {

    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public String getUser(){
        return "hello this is springboot ";
    }
}
