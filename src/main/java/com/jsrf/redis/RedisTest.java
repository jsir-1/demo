package com.jsrf.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest {
    @Autowired
    RedisTemplate redis;

    public void test(){
    }
}
