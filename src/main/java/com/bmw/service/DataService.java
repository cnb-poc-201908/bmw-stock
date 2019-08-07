package com.bmw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class DataService {

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	public void saveData(String key, String json) {
    	ValueOperations<String, String> ops = redisTemplate.opsForValue();
    	ops.set(key, json);
	}
}
