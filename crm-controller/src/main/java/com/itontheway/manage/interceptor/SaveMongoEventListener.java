package com.itontheway.manage.interceptor;

import com.itontheway.manage.common.AutoIncKey;
import com.itontheway.manage.entity.common.IncInfo;
import org.bson.Document;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author: 公众号 itontheway
 * @description: 对mongodb保存过程进行监听，实现主键的递增替换
 * @date 2020/3/16 21:19
 */
@Component
public class SaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 在保存的时候进行监听，通过反射对ID进行自增
     * 此时MongoDB data已经将要操作的实体类转为Document{@link Document}对象，
     * 所以这时候应该对document对象进行换ID
     * @param event
     */
    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {
        //得到操作的实体类对象
        Object source = event.getSource();
        //spring-mongo-data与MongoDB交互的document对象
        Document document = event.getDocument();
        if (Objects.nonNull(source)) {
            //利用反射进行相关操作
            ReflectionUtils.doWithFields(source.getClass(), field -> {
                //使操作的成员可访问
                ReflectionUtils.makeAccessible(field);
                //该字段是否使用自增注解且是Number类型
                if (field.isAnnotationPresent(AutoIncKey.class) && field.get(source) instanceof Number) {
                    String collectionName = source.getClass().getSimpleName().substring(0, 1).toLowerCase()
                            + source.getClass().getSimpleName().substring(1);
                    //判断document不能为空
                    Assert.notNull(document,"event.document must not be null");
                    //获取自增主键
                    Long incrId = getIncrId(collectionName);
                    //对ID进行替换
                    document.put("_id", incrId);
                    field.set(source,incrId);
                }
            });
        }
        super.onBeforeSave(event);
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/16 21:30
     * @Desc 返回下一个自增ID
     * @Param [collectionName]
     * @Return java.lang.Long
     **/
    private Long getIncrId(String collectionName) {
        Query query = new Query(Criteria.where("collectionName").is(collectionName));
        Update update = new Update();
        update.inc("incrId");
        FindAndModifyOptions options = FindAndModifyOptions.options();
        options.upsert(true);//没有就新增
        options.returnNew(true);//返回最新
        IncInfo incInfo = mongoTemplate.findAndModify(query, update, options, IncInfo.class);
        Assert.notNull(incInfo,"主键自增时返回参数异常");
        return incInfo.getIncrId();
    }

}
