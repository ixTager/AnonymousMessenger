package com.anonchat.anonymousmessenger.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "dialogs")
public class Dialog {
    @Id
    String id;

    @Column(name = "createdAt")
    Instant createdAt;

    @Column(name = "lastMessageSentAt")
    Instant lastMessageSentAt;

    @Column(name ="creatorNickname")
    String creatorNickname;

    @Column(name ="lastMessage")
    String lastMessage;

}
