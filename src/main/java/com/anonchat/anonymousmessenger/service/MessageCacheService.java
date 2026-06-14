package com.anonchat.anonymousmessenger.service;

import com.anonchat.anonymousmessenger.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class MessageCacheService {
    private static final String HISTORY_KEY ="chat:history";
    private static final String SESSION_PREFIX = "session:";
    private static final int HISTORY_MAX = 5;

    private final RedisTemplate<String, Message> redis;
    private final StringRedisTemplate stringRedis;

    public void save(Message message){
        redis.opsForList().rightPush(HISTORY_KEY,message);

        redis.opsForList().trim(HISTORY_KEY, -HISTORY_MAX, -1);
        log.info("Cached message id={}", message.getId());
    }

    public List<Message> getHistory(){
        List<Message> history = redis.opsForList().range(HISTORY_KEY, 0, -1);
        return history != null ? history : List.of();
    }

    public void saveSession(String sessionId, String nickname){
        stringRedis.opsForValue().set(SESSION_PREFIX + sessionId, nickname, Duration.ofHours(1));
    }

    public String getNickname(String sessionId){
        return stringRedis.opsForValue().get(SESSION_PREFIX + sessionId);
    }

    public void removeSession(String sessionId){
        stringRedis.delete(SESSION_PREFIX + sessionId);
    }

}
