package com.itontheway.manage.dao;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:22
 */
@Repository
public interface BaseDao<T> {
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 17:00
     * @Desc //TODO 单条保存
     * @Param [entity]
     * @Return java.lang.Long
     **/
    Long insert(T entity);

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 17:00
     * @Desc //TODO 单条更新
     * @Param [entity]
     * @Return java.lang.Long
     **/
    boolean update(T entity);

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 17:00
     * @Desc //TODO 批量保存
     * @Param [entity]
     * @Return java.lang.Long
     **/
    boolean saveBatchByList(List<T> entityList);

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 17:00
     * @Desc //TODO 批量保存
     * @Param [entity]
     * @Return java.lang.Long
     **/
    boolean saveBatchByMap(Map<String, Object> params);

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 17:00
     * @Desc //TODO 批量更新
     * @Param [entity]
     * @Return java.lang.Long
     **/
    boolean updateBatchByMap(Map<String, Object> params);

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 17:00
     * @Desc //TODO 批量更新根据经IDS
     * @Param [entity]
     * @Return java.lang.Long
     **/
    boolean updateBatchByIds(Long[] ids);

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 17:00
     * @Desc //TODO 批量更新根据经IDS
     * @Param [entity]
     * @Return java.lang.Long
     **/
    boolean updateBatchByListIds(List<T> ids);

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 17:02
     * @Desc //TODO 得到数量
     * @Param [t]
     * @Return java.lang.Integer
     **/
    Integer getCount(T t);

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/9 17:00
     * @Desc //TODO 根据ID查询
     * @Param [entity]
     * @Return java.lang.Long
     **/
    T findById(Long id);


    List<T> findListByEntity(T t);

    List<T> findListByMap(Map<String, Object> params);

    boolean deleteById(Long id);

    boolean deleteBatchByIds(Long[] ids);

    boolean deleteBatchByListIds(List<T> ids);

    boolean deleteBatchByMap(Map<String, Object> params);

}
