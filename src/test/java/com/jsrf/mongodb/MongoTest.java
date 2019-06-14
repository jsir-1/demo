package com.jsrf.mongodb;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jsrf.mongodb.entity.MongodbDO;
import com.jsrf.mongodb.entity.User;
import com.jsrf.mongodb.service.IMongoDBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {
    @Autowired
    IMongoDBService mongoDBService;

    @Test
    public void insert() {
        User user = new User();
        user.setId("1");
        user.setName("zhangsan");
        user.setGender("man");
        user.setPassword("123456");
        user.setCreateTime(new Date());

        MongodbDO mongodbDO = new MongodbDO();
        mongodbDO.setOperator("jsrf");
        mongodbDO.setData(user);
        mongodbDO.setId(UUID.randomUUID().toString());
        mongoDBService.insert(mongodbDO);
    }

    @Test
    public void insertAll() {
        ArrayList<User> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i + "");
            user.setName("zhangsan" + i);
            user.setGender("man");
            user.setPassword("" + i);
            user.setCreateTime(new Date());
            list.add(user);
        }
        mongoDBService.insertAll(list);
    }

    @Test
    public void remove() {
        ArrayList<Object> list = Lists.newArrayList();
        list.add("0");
        list.add("1");
        list.add("2");
        mongoDBService.remove(list, User.class);
    }

    @Test
    public void updateById() {
        Map<String, Object> map = Maps.newConcurrentMap();
        map.put("password", "33333");
        mongoDBService.updateById("3",map, User.class);
    }

    @Test
    public void updateByCretia() {
        Map<String, Object> map = Maps.newConcurrentMap();
        Criteria criteria = Criteria.where("id").is("7");
        map.put("name", "jsrf");
        mongoDBService.updateByCretia(criteria, map, User.class);
    }

    @Test
    public void findAll(){
        List<User> all = mongoDBService.findAll(Criteria.where("gender").is("man"), User.class);
        System.out.println(all);
    }

    @Test
    public void findAllPage(){
        User user = new User();
        user.setGender("man");
        PageRequest pageRequest = PageRequest.of(0, 7, Sort.by(Sort.Direction.DESC, "name"));
        Page<User> all = (Page<User>) mongoDBService.findAll(user, pageRequest);
        List<User> content = all.getContent();
        System.out.println(content);
    }

    @Test
    public void createIndex(){
        mongoDBService.createIndex();
    }
}
