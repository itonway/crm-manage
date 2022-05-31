package com.itontheway.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itontheway.manage.dao.BaseDao;
import com.itontheway.manage.entity.common.BaseEntity;
import com.itontheway.manage.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:22
 */
@Service
public abstract class BaseServiceImpl<T extends BaseEntity> implements IBaseService<T> {

    @Resource
    private BaseDao<T> baseDao;

    @Override
    public Long insert(T entity) {
        return baseDao.insert ( entity );
    }

    @Override
    public boolean update(T entity) {
        return baseDao.update ( entity );
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        Long id = entity.getId ();
        if (null == id) {
            baseDao.insert ( entity );
        } else {
            baseDao.update ( entity );
        }
        return true;
    }

    @Override
    public boolean saveBatchByList(List<T> entityList) {
        return baseDao.saveBatchByList ( entityList );
    }

    @Override
    public boolean saveBatchByMap(Map<String, Object> params) {
        return baseDao.saveBatchByMap ( params );
    }

    @Override
    public boolean updateBatchByMap(Map<String, Object> params) {
        return baseDao.updateBatchByMap ( params );
    }

    @Override
    public boolean updateBatchByIds(Long[] ids) {
        return baseDao.updateBatchByIds ( ids );
    }

    @Override
    public boolean updateBatchByListIds(List<T> ids) {
        return baseDao.updateBatchByListIds ( ids );
    }

    @Override
    public Integer getCount(T t) {
        return baseDao.getCount ( t );
    }

    @Override
    public T findById(Long id) {
        return (T) baseDao.findById ( id );
    }

    @Override
    public T findByEntity(T t) {
        return baseDao.findByEntity ( t );
    }

    @Override
    public List<T> findListByEntity(T t) {
        return baseDao.findListByEntity ( t );
    }

    @Override
    public List<T> findListByMap(Map<String, Object> params) {
        return baseDao.findListByMap ( params );
    }

    @Override
    public PageInfo<T> findListPageByEntity(Integer page, Integer limit, T t) {
        PageHelper.startPage ( page, limit, true );
        List<T> listByEntity = baseDao.findListByEntity ( t );
        PageInfo<T> pageInfo = new PageInfo<> ( listByEntity );
        return pageInfo;
    }

    @Override
    public PageInfo<T> findListPageByMap(Integer page, Integer limit, Map<String, Object> params) {
        PageHelper.startPage ( page, limit, true );
        List<T> listByMap = baseDao.findListByMap ( params );
        PageInfo<T> pageInfo = new PageInfo<> ( listByMap );
        return pageInfo;
    }

    @Override
    public boolean deleteById(Long id) {
        return baseDao.deleteById ( id );
    }

    @Override
    public boolean deleteBatchByIds(Long[] ids) {
        return baseDao.deleteBatchByIds ( ids );
    }

    @Override
    public boolean deleteBatchByListIds(List<T> ids) {
        return baseDao.deleteBatchByListIds ( ids );
    }

    @Override
    public boolean deleteBatchByMap(Map<String, Object> params) {
        return baseDao.deleteBatchByMap ( params );
    }
}
