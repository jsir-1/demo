package com.jsrf.mongodb.service.impl;

import com.jsrf.mongodb.service.IMongoDBService;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * @author jsrf
 */
@Service
public class MongoDBServiceImpl implements IMongoDBService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入对象
     *
     * @param objectToSave
     */
    @Override
    public void insert(Object objectToSave) {
        mongoTemplate.insert(objectToSave);
    }

    public static void main(String[] args) {
    }

    @Override
    public void insertAll(Collection<? extends Object> objectsToSave) {
        mongoTemplate.insertAll(objectsToSave);
    }

    /**
     * 根据传入的ids 对指定集合进行删除
     *
     * @param ids
     * @param entityClass
     * @return
     */
    @Override
    public long remove(List<Object> ids, Class<?> entityClass) {
        Criteria criatira = Criteria.where("id").in(ids);
        Query query = new Query(criatira);
        DeleteResult result = mongoTemplate.remove(query, entityClass);
        return result.getDeletedCount();
    }

    /**
     * 根据id进行更新
     *
     * @param id
     * @param map
     * @param entityClass
     * @return
     */
    @Override
    public long updateById(Object id, Map<String, Object> map, Class<?> entityClass) {
        Update update = new Update();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                update.set(entry.getKey(), entry.getValue());
            } else {
                update.unset(entry.getKey());
            }
        }

        UpdateResult updateMulti = mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(id)), update, entityClass);
        return updateMulti.getModifiedCount();
    }

    /**
     * 根据条件更新
     *
     * @param criteria
     * @param map
     * @param entityClass
     * @return
     */
    @Override
    public long updateByCretia(Criteria criteria, Map<String, Object> map, Class<?> entityClass) {
        Update update = new Update();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                update.set(entry.getKey(), entry.getValue());
            } else {
                update.unset(entry.getKey());
            }
        }

        UpdateResult updateMulti = mongoTemplate.updateMulti(new Query(criteria), update, entityClass);
        return updateMulti.getModifiedCount();
    }

    /**
     * 根据传入实体，利用反射进行查询
     *
     * @param object
     * @param page
     * @return
     */
    @Override
    public Page<?> findAll(Object object, Pageable page) {
        Criteria criatira = new Criteria();
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object val = field.get(object);
                if (val != null) {
                    String key = field.getName();
                    criatira.and(key).is(val);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findAll(criatira, page, object.getClass());
    }

    /**
     * 根据传入的查询条件，进行查询
     *
     * @param criatira
     * @param page
     * @param entityClass
     * @return
     */
    public <K> Page<K> findAll(Criteria criatira, Pageable page, Class<K> entityClass) {
        Query query = new Query(criatira);
        //查询数量
        long totalCount = this.mongoTemplate.count(query, entityClass);
        Page<K> pages;
        List<K> list;
        if (totalCount > 0) {
            list = mongoTemplate.find(query.with(page), entityClass);
            pages = new PageImpl<>(list, page, totalCount);
        } else {
            pages = new PageImpl<>(Collections.emptyList());
        }
        return pages;
    }

    /**
     * 查询所有满足条件的记录，不分页
     *
     * @param criatira
     * @param entityClass
     * @return
     */
    @Override
    public <K> List<K> findAll(Criteria criatira, Class<K> entityClass) {
        Query query = new Query(criatira);
        return mongoTemplate.find(query, entityClass);
    }

    /**
     * 创建索引
     */
    @Override
    public void createIndex() {
        MongoCollection<Document> user = mongoTemplate.getCollection("user");
        //1表示升序
        user.createIndex(new BasicDBObject().append("name", 1));
    }

}
