package com.anonchat.anonymousmessenger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final RabbitTemplate rabbitTemplate;
    private final MessageCacheService messageCacheService;
}
