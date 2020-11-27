package com.itontheway.manage.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class DataListener4ConsumerExample<T> extends AnalysisEventListener<T> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;

    /**
     * 接收lambda表达式，如
     * lambda表达式
     * Consumer<OrderCategoryData> consumer = orderCategoryData -> orderCategoryService.saveCategoryAndRelFromExcel(orderCategoryData);
     * EasyExcel.read(filePath, OrderCategoryData.class, new DataListener<OrderCategoryData>(consumer)).sheet().doRead();
     */
    private Consumer<T> consumer;

    List<T> list = new ArrayList<>();

    public DataListener4ConsumerExample(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("Excel解析失败，但是继续解析下一行", exception);
    }

    /**
     * 这个每一条数据解析都会来调用
     * @param data
     * @param context
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            excuteData();
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     * 这里也要保存数据，确保最后遗留的数据也存储到数据库
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        excuteData();
    }

    private void excuteData () {

        log.info(list.size()+"条数据，开始存储数据库！");

        // 这里用到了Java8的函数式编程，consumer为Excel数据的处理方法
        list.forEach( termData -> this.consumer.accept(termData) );

        log.info("存储数据库成功！");

    }
}
