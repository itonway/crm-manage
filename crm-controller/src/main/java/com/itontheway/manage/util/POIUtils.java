package com.itontheway.manage.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xiegl
 * @desc
 * @Date 2021-2-26 10:56
 */
public class POIUtils {

    public static void main(String[] args) throws Exception {
        List<List<String>> excelData = getExcelData();
        for (List<String> dataList : excelData) {
            for(int i = 0 ; i < dataList.size() ; i++){
                System.out.println(i + "------------" + dataList.get(i));
            }
        }
    }

    public static List<List<String>> getExcelData() throws Exception {
        List<List<String>> excelDataList = convertExcelData("I:\\project\\gilite\\gite\\ds-excel.xlsx");
        return excelDataList;
    }

    public static List<List<String>> convertExcelData(String filePath) throws Exception {
        List<List<String>> list = new ArrayList<>();
        List<String> dsColumnList = new ArrayList<>();
        dsColumnList.add("教师姓名");
        dsColumnList.add("工号");
        //dsColumnList.add("手机");
        //dsColumnList.add("机构编号");
        dsColumnList.add("机构名称");

        File file = new File(filePath);
        Workbook workbook = getWookBook(file);
        Sheet sheet = workbook.getSheetAt(0);
        int firstRowNum = sheet.getFirstRowNum();
        Row rowHead = sheet.getRow(firstRowNum);
        List<Integer> columnIndexList = new ArrayList<>();
        for (Cell cell : rowHead) {
            String name = convertCellValueToString(cell);
            int columnIndex = cell.getColumnIndex();
            for (String s : dsColumnList) {
                if(name.equals(s)){
                    columnIndexList.add(columnIndex);
                }
            }
        }

        if(CollectionUtils.isEmpty(columnIndexList)){
            return list;
        }

        int rowStart  = firstRowNum + 1;
        int rowEnd = sheet.getPhysicalNumberOfRows();

        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
            Row row = sheet.getRow(rowNum);
            List<String> list1 = new ArrayList<>();
            for (Integer integer : columnIndexList) {
                Cell cell = row.getCell(integer);
                String value = convertCellValueToString(cell);
                list1.add(value);
                //System.out.println(value);
            }
            list.addAll(Collections.singleton(list1));
        }
        return list;
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
