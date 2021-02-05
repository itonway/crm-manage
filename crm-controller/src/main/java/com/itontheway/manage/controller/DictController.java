package com.itontheway.manage.controller;

import com.itontheway.manage.common.Result;
import com.itontheway.manage.entity.vo.Dict;
import com.itontheway.manage.service.IDictService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/16 20:28
 */
@Api(value = "字典管理",tags = "字典管理")
@Slf4j
@RestController
@RequestMapping("dict")
public class DictController {

    @Autowired
    IDictService dictService;
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/16 20:45
     * @Desc 保存和修改字典
     * @Param [dict, bindingResult]
     * @Return void
     **/
    @GetMapping(value = "saveOrUpdateDict")
    @ApiOperation(value = "保存和修改字典",notes = "保存和修改字典",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dict", value = "用户", dataType = "object", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public void saveOrUpdateDict(@Validated Dict dict, BindingResult bindingResult){
        Long id = dict.getId();
        if(id == null){
            dictService.insert(dict);
            return;
        }
        Dict byId = dictService.findById(id);
        dictService.update(byId,dict);
        log.info("保存或修改成功："+dict);
    }
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/16 20:40
     * @Desc 根据字典ID查询用户
     * @Param [id]
     * @Return com.itontheway.manage.entity.Dict
     **/
    @RequestMapping(value = "findDictById",method = RequestMethod.GET)
    @ApiOperation(value = "根据字典ID查询用户",notes = "根据用户ID查询用户",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户", dataType = "int", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public Dict findDictById(Long id){
        return dictService.findById(id);
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/16 12:35 
     * @Desc 查询字典列表
     * @Param [Dict]
     * @Return java.util.List<com.itontheway.manage.entity.Dict>
     **/
    @RequestMapping(value = "findDictList",method = RequestMethod.GET)
    @ApiOperation(value = "查询字典列表",notes = "查询字典列表",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dict", value = "用户", dataType = "object", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public List<Dict> findDictList(Dict dict){
        List<Dict> DictList = dictService.findListByEntity(dict);
        return DictList;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/16 12:35
     * @Desc 根据ID删除字典
     * @Param [Dict]
     * @Return java.util.List<com.itontheway.manage.entity.Dict>
     **/
    @RequestMapping(value = "deleteDictById",method = RequestMethod.GET)
    @ApiOperation(value = "根据ID删除字典",notes = "根据ID删除字典",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "字典ID", dataType = "int", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public void deleteDictById(Long id){
        dictService.deleteById(id);
    }

}
