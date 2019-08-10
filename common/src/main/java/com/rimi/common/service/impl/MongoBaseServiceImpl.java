package com.rimi.common.service.impl;

import com.rimi.common.service.IBaseService;
import com.rimi.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;

/**
 * MongoDB基本DAO
 * @param <R> 继承MongoRepository的接口
 * @param <T> 实体类型
 * @param <ID> 主键类型
 * @author shangzf
 */
public class MongoBaseServiceImpl<R extends MongoRepository<T, ID>, T, ID extends Serializable> implements IBaseService<T, ID> {

    @Autowired
    private R repository;

    @Override
    public T selectOne(T entity) {
        return repository.findOne(Example.of(entity)).orElse(null);
    }

    @Override
    public T selectById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> selectListAll() {
        return repository.findAll();
    }

    @Override
    public List<T> selectListAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void updateSelectiveById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        repository.save(entity);
    }

    @Override
    public void updateById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void insertSelective(T entity) {
        EntityUtils.setCreateAndUpdateInfo(entity);
        repository.insert(entity);
    }

    @Override
    public void insert(T entity) {
        EntityUtils.setCreateAndUpdateInfo(entity);
        repository.insert(entity);
    }

    @Override
    public Long selectCount(T entity) {
        return repository.count(Example.of(entity));
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<T> selectList(T entity) {
        return repository.findAll(Example.of(entity));
    }

    public Page<T> selectPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
