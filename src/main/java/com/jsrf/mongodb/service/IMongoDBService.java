package com.jsrf.mongodb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IMongoDBService {
    void insert(Object objectToSave);

    void insertAll(Collection<? extends Object> objectsToSave);

    long remove(List<Object> ids, Class<?> entityClass);

    long updateById(Object id, Map<String, Object> map, Class<?> entityClass);

    long updateByCretia(Criteria criteria, Map<String, Object> map, Class<?> entityClass);

    Page<?> findAll(Object object, Pageable page);

    <K> List<K> findAll(Criteria criatira, Class<K> entityClass);

    void createIndex();
}
