package com.anonchat.anonymousmessenger.rabbitmq;

import com.anonchat.anonymousmessenger.config.RabbitMQConfig;
import com.anonchat.anonymousmessenger.entity.Message;
import com.anonchat.anonymousmessenger.repository.DialogRepository;
import com.anonchat.anonymousmessenger.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageConsumer {
    private final MessageRepository messageRepository;
    private final DialogRepository dialogRepository;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consume(Message message) {
        messageRepository.save(message);

        dialogRepository.findById(message.getDialogId()).ifPresent(d -> {
            d.setLastMessage(message.getContent());
            d.setLastMessageSentAt(message.getSentAt());
            dialogRepository.save(d);
        });

        rabbitTemplate.convertAndSend(
                "amq.topic",
                "topic." + message.getDialogId(),
                message
        );
    }
}
