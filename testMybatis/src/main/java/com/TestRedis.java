package com;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1(){


        redisTemplate.boundHashOps("name").put("id","zhangsan");
        redisTemplate.boundHashOps("name").put("id1","zhangsan1");
        redisTemplate.boundHashOps("name").put("id2","zhangsan2");
        System.out.println(redisTemplate.boundHashOps("name").get("id"));

        List list = new ArrayList();
        for (Object name : redisTemplate.boundHashOps("name").keys()) {
            System.out.println("name = " + name);
            if(name.equals("id2"))
            {
                list.add(name);
            }
        }
        for (Object name : redisTemplate.boundHashOps("name").values()) {
            System.out.println("name = " + name);
        }
        list.add("id");
        for (Object name : redisTemplate.boundHashOps("name").multiGet(list)) {
            System.out.println("name = " + name);
        }

    }

}
