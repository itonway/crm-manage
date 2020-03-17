package com.itontheway.manage.entity.common;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.itontheway.manage.common.Const;

/**
 * @Author 公众号 itontheway
 * @Date 2020/3/17 19:46
 * @Desc 性别转换
 **/
public class SexConverter implements Converter<String> {
    private static final String MEN = "男";
    private static final String WOMEN = "女";

    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/17 19:46
     * @Desc 读的时候调用 ， excel中的值，转换为字典值  男->1   女->2
     * @Param [cellData, excelContentProperty, globalConfiguration]
     * @Return java.lang.String
     **/
    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue ();
        if (MEN.equals ( stringValue )) {
            return Const.SEX_MEN;
        } else if (WOMEN.equals ( stringValue )) {
            return Const.SEX_WOMEN;
        }
        return Const.SEX_MEN;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/17 19:47
     * @Desc 写的时候调用 ，字典项的值，转换为待导出的值 1->男 2->女
     * @Param [s, excelContentProperty, globalConfiguration]
     * @Return com.alibaba.excel.metadata.CellData
     **/
    @Override
    public CellData convertToExcelData(String s, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        CellData cellData = null;
        if (Const.SEX_MEN.equals ( s )) {
            cellData = new CellData( MEN );
        } else if (Const.SEX_WOMEN.equals ( s )) {
            cellData = new CellData( WOMEN );
        }
        return cellData;
    }
}
