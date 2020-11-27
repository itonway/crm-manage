//package com.itontheway.manage.controller;
//
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.api.ApiController;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageInfo;
//import com.itontheway.manage.entity.vo.Org;
//import com.itontheway.manage.service.IOrgService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @Author: xiegl
// * @desc https://www.jb51.net/article/195179.htm
// * @Date 2020-11-27 17:35
// */
//@RestController
//public class OrgController extends ApiController {
//    @Resource
//    private IOrgService orgService;
//
//    @RequestMapping()
//    public PageInfo<Org> findPage(Integer page,Integer limit){
//        IPage<Org> page = new Page<>(page, limit);
//        orgService.getBaseMapper().selectPage()
//    }
//}
