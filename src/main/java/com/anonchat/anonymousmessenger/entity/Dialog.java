package com.anonchat.anonymousmessenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dialog {
    @Id
    String id;

    Instant createdAt;
    Instant lastMessageSentAt;
    String creatorNickname;
    String lastMessage;

}
