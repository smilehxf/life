package com.smile.life.utils;


import com.google.common.collect.Lists;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public abstract class CrudService<T, D extends MongoRepository<T, String>> {

    /**
     * 从子类注入数据访问对象
     *
     * @return
     */
    protected abstract D getRepo();

    public T findById(String id) {
        if (this.getRepo().findById(id).isPresent())
            return this.getRepo().findById(id).get();
        else
            return null;
    }

    public List<T> findAll() {
        Iterable<T> iter = this.getRepo().findAll();
        return Lists.newArrayList(iter);
    }

    //分页
    public List<T> findAll(int page, int size) {
        Iterable<T> iter = this.getRepo().findAll(PageRequest.of(page, size));
        return Lists.newArrayList(iter);
    }

    //排序
    public List<T> findAll(Sort sort) {
        Iterable<T> iter = this.getRepo().findAll(sort);
        return Lists.newArrayList(iter);
    }

    @Transactional
    public void save(T entity) {
        if (entity == null)
            return;
        this.getRepo().save(entity);
    }


    public void delete(String id) {
        this.getRepo().deleteById(id);
    }


    public void deleteAll() {
        this.getRepo().deleteAll();
    }


}
