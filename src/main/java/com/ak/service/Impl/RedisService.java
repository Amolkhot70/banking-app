package com.ak.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    // Constructor to initialize ObjectMapper with JavaTimeModule
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Register the JavaTimeModule for LocalDate and other Java 8 types
    }

    public void set(String id, Object o, Long ttl) {
        try {
            // Serialize the object to JSON using ObjectMapper with JavaTimeModule
            String jsonValue = objectMapper.writeValueAsString(o);
            this.redisTemplate.opsForValue().set(id, jsonValue, ttl, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("Error setting value in Redis for key {}: ", id, e.getMessage(), e);
        }
    }

    public <T> T get(String id, Class<T> clazz) {
        try {
            // Get the value from Redis
            String jsonValue = (String) this.redisTemplate.opsForValue().get(id);
            if (jsonValue != null) {
                // Deserialize the value back to the object using ObjectMapper with JavaTimeModule
                return objectMapper.readValue(jsonValue, clazz);
            }
        } catch (Exception e) {
            log.error("Error getting value from Redis for key {}: ", id, e);
        }
        return null;
    }


//    public <T> T get(Long id, Class<T> expenseDtoClass) {
//        try {
//            Object o = this.redisTemplate.opsForValue().get(id);
//            ObjectMapper mapper = new ObjectMapper();
//            return mapper.readValue(o.toString(), expenseDtoClass);
//
//        } catch (Exception e) {
//         log.error("Error : ",e);
//         return null;
//        }
//    }
//
//    public void set(Long id, Object o,Long ttl) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonValue = mapper.writeValueAsString(o);
//            this.redisTemplate.opsForValue().set(id,jsonValue,ttl, TimeUnit.SECONDS);
//
//        } catch (Exception e) {
//            log.error("Error : ",e);
//        }
//    }
}
