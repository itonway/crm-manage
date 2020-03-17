package com.itontheway.manage.entity.form;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.itontheway.manage.entity.common.SexConverter;
import lombok.Data;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/17 20:05
 */
@Data
public class UserImport {
    @ExcelProperty(value = "用户名", index = 0)
    @ColumnWidth(value = 50)
    private String userName;
    @ExcelProperty(value = "登录名", index = 1)
    private String loginName;
    @ExcelProperty(value = "年龄", index = 2)
    private Integer age;
    @ExcelProperty(value = "邮箱", index = 3)
    @ColumnWidth(value = 50)
    private String email;
    @ExcelProperty(value = "性别", index = 4,converter = SexConverter.class)
    private String sex;
    @ExcelProperty(value = "手机号", index = 5)
    private String mobile;
    @ExcelProperty(value = "创建日期", index = 6)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ColumnWidth(value = 50)
    private String createTimeStr;
}
