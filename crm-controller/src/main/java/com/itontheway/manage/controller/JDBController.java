package com.itontheway.manage.controller;

import com.itontheway.manage.util.POIUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @Author: xiegl
 * @desc JDBC管理
 * @Date 2021-2-26 10:51
 */
@Slf4j
@Api(value = "JDBC管理",tags = {"JDBC管理"})
@RestController
@RequestMapping(value = "jdbc")
public class JDBController {


    @GetMapping(value = "jdbcBatchInsert")
    @ApiOperation(value = "JDBC批量导入数据",notes = "JDBC批量导入数据",produces = "application/json", httpMethod = "GET")
    public void jdbcBatchInsert(){
        String driverClassName = "com.mysql.cj.jdbc.Driver";    //启动驱动
        String url = "jdbc:mysql://localhost:3308/test";    //设置连接路径
        String username = "root";    //数据库用户名
        String password = "123";    //数据库连接密码
        Connection con = null;        //连接
        PreparedStatement pst = null;    //使用预编译语句
        ResultSet rs = null;    //获取的结果集
        try {
            List<List<String>> excelDataList = POIUtils.getExcelData();
            String str = "tea_name,tea_no,org_name";
            String[] split = str.split(",");
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO T_TEA_ORG (").append(str).append(") VALUES (");
            StringBuilder value = new StringBuilder();
            for(int i = 0 ; i < split.length ; i++){
                value.append("?,");
            }
            sql.append(value.substring(0,value.length()-1)).append(")");
            System.out.println("待执行的SQL............"+sql);
            Class.forName(driverClassName); //执行驱动
            con = DriverManager.getConnection(url, username, password); //获取连接
            pst = con.prepareStatement(sql.toString());
            final int batchSize = 1000;
            int count = 0;
            for (List<String> dataList : excelDataList) {
                int m = 1;
                for(int i = 0 ; i < dataList.size() ; i++){
                    pst.setString(m++, dataList.get(i));
                }
                pst.addBatch();
                if(++count % batchSize == 0) {
                    pst.executeBatch();
                }
            }
            pst.executeBatch(); //insert remaining records
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();  //必须要关
            } catch (Exception e) {
            }
        }
    }

}
