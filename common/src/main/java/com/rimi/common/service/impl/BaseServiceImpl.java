package com.rimi.common.service.impl;

import com.rimi.common.service.IBaseService;
import com.rimi.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseServiceImpl<M extends Mapper<T>, T,ID extends Serializable> implements IBaseService<T,ID> {

    @Autowired
    protected M mapper;

    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public T selectById(ID id) {
        return mapper.selectByPrimaryKey(id);
    }


    @Override
    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }

    @Override
    public List<T> selectListAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> selectListAll(Sort sort) {
        //获取泛型类的class
        Class<T> aClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Example example = new Example(aClass);
        //遍历需要排序的字段
        for (Sort.Order next : sort) {
            String property = next.getProperty();
            if (next.getDirection().isAscending()) {
                example.orderBy(property).asc();
            } else {
                example.orderBy(property).desc();
            }
        }
        return mapper.selectByExample(example);
    }


    @Override
    public Long selectCount(T entity) {
        return (long) mapper.selectCount(entity);
    }

    @Override
    public void insert(T entity) {
        EntityUtils.setCreateAndUpdateInfo(entity);
        mapper.insert(entity);
    }

    @Override
    public void insertSelective(T entity) {
        EntityUtils.setCreateAndUpdateInfo(entity);
        mapper.insertSelective(entity);
    }

    @Override
    public void delete(T entity) {
        mapper.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKey(entity);
    }

    @Override
    public void updateSelectiveById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void deleteAll() {
        
    }
}
