package com.yinziming.part12.crud.dao;

import java.util.Collection;

public interface BaseDao<T> {
    T get(int id);

    Collection<T> getAll();

    void save(T t);

    void delete(int id);
}
