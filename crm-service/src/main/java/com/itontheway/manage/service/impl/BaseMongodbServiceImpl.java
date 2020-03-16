package com.itontheway.manage.service.impl;

import com.github.pagehelper.PageInfo;
import com.itontheway.manage.service.IBaseMongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:22
 */
@Service
public abstract class BaseMongodbServiceImpl<T> implements IBaseMongodbService<T> {
    protected abstract Class<T> getEntityClass();
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Long insert(T t) {
        mongoTemplate.save(t);
        return 1L;
    }
    @Override
    public boolean update(T t,T t1) {
        Query query = getQueryByObject(t);
        Update update = getUpdateByObject(t1);
        this.mongoTemplate.updateFirst(query,update,this.getEntityClass());
        return true;
    }
    @Override
    public T findById(Long id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }
    @Override
    public List<T> findListByEntity(T t) {
        Query query = getQueryByObject(t);
        return mongoTemplate.find(query, this.getEntityClass());
    }
    @Override
    public T findByEntity(T t) {
        Query query = getQueryByObject(t);
        return mongoTemplate.findOne(query, this.getEntityClass());
    }
    @Override
    public PageInfo<T> findListPageByEntity(Integer page, Integer limit, T t) {
        Query query = getQueryByObject(t);
        query.skip(page);
        query.limit(limit);
        List<T> ts = this.mongoTemplate.find(query, this.getEntityClass());
        PageInfo<T> pageInfo = new PageInfo<> ( ts );
        return pageInfo;
    }
    @Override
    public Integer getCount(T t) {
        Query query = getQueryByObject(t);
        Long count = this.mongoTemplate.count(query, this.getEntityClass());
        return Integer.valueOf(count.toString());
    }
    @Override
    public Long deleteByEntity(T t) {
        return this.mongoTemplate.remove(t).getDeletedCount();
    }
    @Override
    public void deleteById(Long id) {
        Criteria criteria = Criteria.where("_id").is(id);
        if (null != criteria) {
            Query query = new Query(criteria);
            T obj = this.mongoTemplate.findOne(query, this.getEntityClass());
            if (obj != null) {
                this.deleteByEntity(obj);
            }
        }
    }
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/16 17:44
     * @Desc 将查询条件对象转换为query
     * @Param [object]
     * @Return org.springframework.data.mongodb.core.query.Query
     **/
    private Query getQueryByObject(T object) {
        Query query = new Query();
        String[] fileds = getFiledName(object);
        Criteria criteria = new Criteria();
        for (int i = 0; i < fileds.length; i++) {
            String filedName = (String) fileds[i];
            Object filedValue = getFieldValueByName(filedName, object);
            if (filedValue != null) {
                criteria.and(filedName).is(filedValue);
            }
        }
        query.addCriteria(criteria);
        return query;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/16 17:44
     * @Desc 将查询条件对象转换为update
     * @Param [object]
     * @Return org.springframework.data.mongodb.core.query.Update
     **/
    private Update getUpdateByObject(T object) {
        Update update = new Update();
        String[] fileds = getFiledName(object);
        for (int i = 0; i < fileds.length; i++) {
            String filedName = (String) fileds[i];
            Object filedValue =getFieldValueByName(filedName, object);
            if (filedValue != null) {
                update.set(filedName, filedValue);
            }
        }
        return update;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/16 17:45
     * @Desc 获取对象属性返回字符串数组
     * @Param [o]
     * @Return java.lang.String[]
     **/
    private static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; ++i) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/16 17:45
     * @Desc 根据属性获取对象属性值
     * @Param [fieldName, o]
     * @Return java.lang.Object
     **/
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String e = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + e + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[0]);
            return method.invoke(o, new Object[0]);
        } catch (Exception var6) {
            return null;
        }
    }
}
