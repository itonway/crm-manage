package com.itontheway.manage.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 公众号 itontheway
 * @description: excel监听类
 * @date 2020/3/17 20:10
 */
@Slf4j
public class ExcelListener<T> extends AnalysisEventListener<T> {
    private List<T> data = new ArrayList<>();

    public ExcelListener() {

    }

    public ExcelListener(List<T> data) {
        this.data = data;
    }

    @Override
    public void invoke(T object, AnalysisContext analysisContext) {
        log.info( "解析得到的参数:" + JSON.toJSONString ( object ) );
        data.add ( object );
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
