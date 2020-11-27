package com.itontheway.manage.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import com.itontheway.manage.common.FileHandlerUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @Author 公众号 itontheway
 * @Date 2020/3/17 19:53
 * @Desc 导入导出excel
 **/
public class ExcelUtils<T> {

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/17 19:53
     * @Desc 导出数据 head->导出的实体类 listData->待导出的数据，fileName->文件名
     * @Param [request, response, head, listData, fileName]
     * @Return void
     **/
    public static void exportExcel(
            HttpServletRequest request, HttpServletResponse response,
            Class head, List<?> listData, String fileName) throws IOException {
        ServletOutputStream out = response.getOutputStream ();
        String header = request.getHeader ( "User-Agent" );
        fileName = FileHandlerUtils.getFileName ( fileName, header );
        response.setContentType ( "application/vnd.ms-excel" );
        response.setCharacterEncoding ( "UTF-8" );
        response.setHeader ( "Content-disposition", "attachment;filename=" + fileName );
        EasyExcel.write ( out, head ).sheet ( "sheet1" ).doWrite ( listData );
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/17 19:53
     * @Desc 导入数据
     * @Param [file]
     * @Return java.util.List<?>
     **/
    public static List<?> importExcel(MultipartFile file, Class<?> tClass) {
        InputStream inputStream = null;
        List<?> data = new ArrayList<> ();
        try {
            inputStream = new BufferedInputStream ( file.getInputStream () );
            ExcelListener excelListener = new ExcelListener ( data );
            //执行完，才执行listener
            EasyExcel.read ( inputStream, tClass, excelListener ).sheet ().doRead ();
            return data;
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }
        return data;
    }

    /**
     * javaBean数据写入Excle
     * ExcelWriter excelWriter = EasyExcel.write(file).build();
     *
     * @param excelWriter
     * @param sheetNo
     * @param sheetName
     * @param modelClass
     * @param dataList
     */
    public static void writeDataToExcel (ExcelWriter excelWriter, int sheetNo, String sheetName, Class modelClass, List dataList) {
        WriteSheet sheet = EasyExcel.writerSheet(sheetNo, sheetName).head(modelClass).build();
        excelWriter.write(dataList, sheet);
    }

    /**
     * 用Map的方式导出Excel
     *
     * @param excelWriter
     * @param sheetNo
     * @param sheetName
     * @param dataMapList
     */
    public static void writeDataToExcelWithMap (ExcelWriter excelWriter, int sheetNo, String sheetName, List<LinkedHashMap<String, Object>> dataMapList) {

        List<List<String>> excelHeadList = createExcelHeadList(dataMapList.get(0));
        List<List<Object>> excelDataList = createExcelDataList(dataMapList);
        WriteSheet sheet = EasyExcel.writerSheet(sheetNo, sheetName).head(excelHeadList).build();
        excelWriter.write(excelDataList, sheet);
    }

    /**
     * 生成Excel数据导出的表头
     *
     * @param dataMap
     * @return
     */
    public static List<List<String>> createExcelHeadList (Map<String, Object> dataMap) {

        List<List<String>> headList = new ArrayList<>();

        dataMap.forEach( (k, v) -> {
            List<String> tempList = new ArrayList<>();
            tempList.add(k);
            headList.add(tempList);
        });

        return headList;
    }

    /**
     * 生成Excel数据导出的数据
     *
     * @param dataMapList
     * @return
     */
    public static List<List<Object>> createExcelDataList (List<LinkedHashMap<String, Object>> dataMapList) {

        return dataMapList.stream()
                .map( map -> Lists.newArrayList(map.values()) )
                .collect(Collectors.toList());
    }

    /**
     * 通用Excel导入(Web上传)
     *
     * @param inputStream   文件流(MultipartFile -> getInputStream())
     * @param consumer  数据处理逻辑
     * @param dataTemplate  Excel模板
     * @param <T>
     */
    public static<T> void excelImportWeb (InputStream inputStream, Consumer<T> consumer, Class<T> dataTemplate) {

        EasyExcel.read(inputStream, dataTemplate, new DataListener4ConsumerExample<>(consumer)).sheet().doRead();
    }


    public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize, boolean isBold) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //创建字体
        HSSFFont font = workbook.createFont();
        if(isBold){// 标题和表头字体加粗，其他的不加粗
            font.setBold(true);//加粗字体
        }
        font.setFontHeightInPoints(fontSize);
        style.setFont(font);
        return style;
    }

}
