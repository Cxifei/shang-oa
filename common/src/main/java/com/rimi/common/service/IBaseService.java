package com.rimi.common.service;

import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * 基本CRUD
 *
 * @param <T>
 * @param <ID>
 * @author shangzf
 */
public interface IBaseService<T, ID extends Serializable> {
    /**
     * 查询
     *
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    T selectById(ID id);


    /**
     * 查询列表
     *
     * @param entity
     * @return
     */
    List<T> selectList(T entity);


    /**
     * 获取所有对象
     *
     * @return
     */
    List<T> selectListAll();

    /**
     * 获取所有对象，排序
     *
     * @return
     */
    List<T> selectListAll(Sort sort);


    /**
     * 查询总记录数
     *
     * @param entity
     * @return
     */
    Long selectCount(T entity);

    /**
     * 添加
     *
     * @param entity
     */
    void insert(T entity);


    /**
     * 插入 不插入null字段
     *
     * @param entity
     */
    void insertSelective(T entity);

    /**
     * 删除
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 根据Id删除
     *
     * @param id
     */
    void deleteById(ID id);

    /**
     * 删除所有
     */
    void deleteAll();


    /**
     * 根据id更新
     *
     * @param entity
     */
    void updateById(T entity);


    /**
     * 不update null
     *
     * @param entity
     */
    void updateSelectiveById(T entity);

}
