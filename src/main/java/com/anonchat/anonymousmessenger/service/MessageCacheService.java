package com.anonchat.anonymousmessenger.service;

import com.anonchat.anonymousmessenger.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class MessageCacheService {
    private final RedisTemplate<String, Message> redis;

    private final static String CACHE_KEY_PREFIX = "dialog:messages:";
    private final static Duration TTL = Duration.ofHours(1);
    private final static int MAX_CACHE_SIZE = 5;

    public void cacheMessage(Message message) {
        String cacheKey = CACHE_KEY_PREFIX + message.getDialogId();
        redis.opsForList().leftPush(cacheKey, message);
        redis.opsForList().trim(cacheKey, 0, MAX_CACHE_SIZE - 1);
        redis.expire(cacheKey, TTL);
    }

    public List<Message> getMessages(String dialogId) {
        String cacheKey = CACHE_KEY_PREFIX + dialogId;
        return redis.opsForList().range(cacheKey, 0, -1);
    }

    public String getLastMessage(String dialogId) {
        String cacheKey = CACHE_KEY_PREFIX + dialogId;
        Message msg = redis.opsForList().getLast(cacheKey);
        if (msg == null) { return ""; }
        else return msg.getContent();
    }
}
