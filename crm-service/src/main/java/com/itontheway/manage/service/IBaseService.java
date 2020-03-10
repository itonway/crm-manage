package com.itontheway.manage.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
public interface IBaseService<T> {
    Long insert(T entity);

    boolean update(T entity);

    boolean saveOrUpdate(T entity);

    boolean saveBatchByList(List<T> entityList);

    boolean saveBatchByMap(Map<String, Object> params);

    boolean updateBatchByMap(Map<String, Object> params);

    boolean updateBatchByIds(Long[] ids);

    boolean updateBatchByListIds(List<T> ids);

    Integer getCount(T t);

    T findById(Long id);

    List<T> findListByEntity(T t);

    List<T> findListByMap(Map<String, Object> params);

    PageInfo<T> findListPageByEntity(Integer page, Integer limit, T t);

    PageInfo<T> findListPageByMap(Integer page, Integer limit, Map<String, Object> params);

    boolean deleteById(Long id);

    boolean deleteBatchByIds(Long[] ids);

    boolean deleteBatchByListIds(List<T> ids);

    boolean deleteBatchByMap(Map<String, Object> params);
}
