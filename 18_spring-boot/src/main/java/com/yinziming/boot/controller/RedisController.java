package com.yinziming.boot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = {"/hello", "/"}, method = RequestMethod.GET)
    public String helloHandler() {
        return "/hello";
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public String getCount(Map<String, Object> map) {
        List<Count> list = new LinkedList<>();
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        Set<String> keys = stringRedisTemplate.keys("*");
        assert keys != null;
        for (String key : keys) {
            String value = operations.get(key);
            list.add(new Count(key, value));
            System.out.println("key = " + key + ", value = " + value);
        }
        map.put("counts", list);
        return "/count";
    }

    @ResponseBody
    @RequestMapping(value = "/counts", method = RequestMethod.GET)
    public Map<String, String> getCounts() {
        Map<String, String> map = new LinkedHashMap<>();
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        Set<String> keys = stringRedisTemplate.keys("*");
        assert keys != null;
        for (String key : keys) {
            String value = operations.get(key);
            map.put(key, value);
            System.out.println("key = " + key + ", value = " + value);
        }
        return map;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class Count {
        private String url;
        private String count;
    }
}
