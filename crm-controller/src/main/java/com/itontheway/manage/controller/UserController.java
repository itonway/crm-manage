package com.itontheway.manage.controller;

import com.github.pagehelper.PageInfo;
import com.itontheway.manage.common.DateUtils;
import com.itontheway.manage.common.Result;
import com.itontheway.manage.entity.form.UserExport;
import com.itontheway.manage.entity.form.UserImport;
import com.itontheway.manage.entity.vo.User;
import com.itontheway.manage.exception.CustomizeException;
import com.itontheway.manage.service.IUserService;
import com.itontheway.manage.util.ExcelUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
@Slf4j
@Api(value = "用户管理",tags = {"用户管理"})
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController{

    @Resource
    IUserService userService;

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/17 20:51
     * @Desc 批量导出用户
     * @Param [request, response, userParam]
     * @Return void
     **/
    @GetMapping(value = "exportUserBatch")
    @ApiOperation(value = "批量导出用户",notes = "批量导出用户",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", dataType = "object", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public void exportUserBatch(
            HttpServletRequest request, HttpServletResponse response,
            User userParam) throws Exception {
        List<User> userList = userService.findListByEntity ( userParam );
        List<UserExport> userExportList = new ArrayList<>();
        for (User user : userList) {
            UserExport userExport = new UserExport ();
            BeanUtils.copyProperties ( userExport, user );
            userExport.setCreateTimeStr ( DateUtils.formatLocal(user.getCreateTime()));
            userExportList.add ( userExport );
        }
        try {
            ExcelUtils.exportExcel ( request, response, UserExport.class, userExportList, "user.xlsx" );
        } catch (Exception e) {
            log.error ( "导出用户信息失败",e);
        }
    }
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/17 20:51
     * @Desc 批量导入用户
     * @Param [file]
     * @Return com.itontheway.manage.common.Result
     **/
    @PostMapping(value = "importUserBatch",headers = "content-type=multipart/form-data")
    @ApiOperation(value = "批量导入用户",notes = "批量导入用户")
    @ApiImplicitParams({})
    public Result importInfoUserBatch(@RequestParam MultipartFile file) {
        InputStream inputStream = null;
        try {
            List<UserImport> UserImportList = (List<UserImport>) ExcelUtils.importExcel ( file ,UserImport.class);
            if (CollectionUtils.isEmpty ( UserImportList )) {
                return Result.fail("数据不能为空");
            }
            for (UserImport UserImport : UserImportList) {
                User user = new User();
                System.out.println ( "导入的数据：" + UserImport );
                BeanUtils.copyProperties ( user, UserImport );
                userService.saveOrUpdate(user);
            }
            return Result.success("数据导入成功");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/11 16:59
     * @Desc // 保存和修改用户
     * @Param [user, bindingResult]
     * @Return void
     **/
    @GetMapping(value = "saveOrUpdateUser")
    @ApiOperation(value = "保存和修改用户",notes = "保存和修改用户",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", dataType = "object", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public void saveOrUpdateUser(@Validated User user, BindingResult bindingResult){
        userService.saveOrUpdate(user);
    }
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/10 20:36
     * @Desc //分页查询用户列表
     * @Param [page, limit, user]
     * @Return PageInfo<User>
     **/
    @RequestMapping(value = "findUserListPage",method = RequestMethod.GET)
    @ApiOperation(value = "分页查询用户列表",notes = "分页查询用户列表",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "limit", value = "每页显示条数", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "user", value = "用户", dataType = "object", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public PageInfo<User> findUserListPage(Integer page, Integer limit, User user){
        PageInfo<User> listPageByEntity = userService.findListPageByEntity(page, limit, user);
        return listPageByEntity;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/11 10:58
     * @Desc //TODO 根据用户ID查询用户
     * @Param [id]
     * @Return com.itontheway.manage.entity.User
     **/
    @RequestMapping(value = "findUserById",method = RequestMethod.GET)
    //@IgnorIntercept
    @ApiOperation(value = "根据用户ID查询用户",notes = "根据用户ID查询用户",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户", dataType = "int", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public User findUserById(Long id){
        return userService.findById(id);
    }
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 12:35 
     * @Desc //TODO
     * @Param [user]
     * @Return java.util.List<com.itontheway.manage.entity.User>
     **/
    @RequestMapping(value = "findUserList",method = RequestMethod.GET)
    @ApiOperation(value = "查询用户列表",notes = "查询用户列表",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", dataType = "object", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public List<User> findUserList(User user){
        List<User> userList = userService.findListByEntity(user);
        return userList;
    }


    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/10 17:31
     * @Desc //异常处理
     * @Param []
     * @Return java.lang.String
     **/
    @RequestMapping(value = "exceptionHandler",method = RequestMethod.GET)
    @ApiOperation(value = "异常处理",notes = "查异常处理",produces = "application/json", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
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
    @ApiOperation(value = "自定义异常",notes = "自定义异常",produces = "application/json", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public String customizeExceptionHandler() throws CustomizeException {
        throw new CustomizeException("0","自定义异常信息");
    }

}
