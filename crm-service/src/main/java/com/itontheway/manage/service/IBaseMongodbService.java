package com.itontheway.manage.service;
import com.github.pagehelper.PageInfo;

import java.util.List;
/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
public interface IBaseMongodbService<T> {
    Long insert(T entity);
    boolean update(T oldEntity,T newEntity);
    Integer getCount(T t);
    T findById(Long id);
    T findByEntity(T t);
    List<T> findListByEntity(T t);
    PageInfo<T> findListPageByEntity(Integer page, Integer limit, T t);
    void deleteById(Long id);
    Long deleteByEntity(T t);
}
