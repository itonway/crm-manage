package com.itontheway.manage.util;

import com.alibaba.excel.EasyExcel;
import com.itontheway.manage.common.FileHandlerUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
}
