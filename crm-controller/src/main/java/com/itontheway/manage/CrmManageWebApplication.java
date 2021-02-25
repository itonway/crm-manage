package com.itontheway.manage;

import com.itontheway.manage.dynamicdatasource.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 公众号 itontheway
 * @description: 主启动类
 * @date 2020/3/7 12:21
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Import(DataSourceConfig.class)
//dao层所在目录，不然扫描不到
@MapperScan(value = "com.itontheway.manage.dao")
//保证切面可以切入
@EnableAspectJAutoProxy
//保证定时任务执行
@EnableScheduling
@Slf4j
public class CrmManageWebApplication {
//    public static void main(String[] args) {
////        log.info("CrmManageWebApplication 启动开始。。。");
////        SpringApplication.run(CrmManageWebApplication.class);
////        log.info("CrmManageWebApplication 启动成功。。。");
//    }


    public static void main(String[] args) throws Exception {
        checkFile("I:\\project\\gilite\\gite\\ds-excel.xlsx");
    }

    public static void checkFile(String filePath) throws Exception {

        List<String> dtColumnList = new ArrayList<>();
        dtColumnList.add("教师姓名");
        dtColumnList.add("工号");
        dtColumnList.add("手机");
        //dtColumnList.add("机构编号");
        //dtColumnList.add("机构名称");

        File file = new File(filePath);
        Workbook workbook = getWookBook(file);
        Sheet sheet = workbook.getSheetAt(0);
        int firstRowNum = sheet.getFirstRowNum();
        Row rowHead = sheet.getRow(firstRowNum);
        for (Cell cell : rowHead) {
            String stringCellValue = cell.getStringCellValue();
            //System.out.println(stringCellValue);
        }

        int rowStart  = firstRowNum;
        int rowEnd = sheet.getPhysicalNumberOfRows();
        System.out.println("总行数：" + rowEnd);
        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
            Row row = sheet.getRow(rowNum);
            int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
            for (int c = 0; c < cellCount; c++) {
                Cell cell = row.getCell(c);
                String name = convertCellValueToString(cell);
                System.out.println(name);
                for (String s : dtColumnList) {
                    if(name.equals(s)){
                        String name1 = convertCellValueToString(cell);
                        System.out.println(name1);
                    }
                }
            }
        }

    };


    private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                Double doubleValue = cell.getNumericCellValue();
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:
                break;
            case FORMULA:
                returnValue = cell.getCellFormula();
                break;
            default:
                break;
        }
        return returnValue;
    }


    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static Workbook getWookBook(File file) throws IOException {
        String fileName = file.getName();
        Workbook workbook = null;
        InputStream is = new FileInputStream(file);
        if(fileName.endsWith(XLS)){
            workbook = new HSSFWorkbook(is);

        }else if(fileName.endsWith(XLSX)){
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

}
