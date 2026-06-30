package com.anonchat.anonymousmessenger.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "dialog_id")
    String dialogId;

    @Column(name = "nickname", length = 100)
    String nickname;

    @Column(name = "content")
    String content;

    @Column(name = "sentAt")
    Instant sentAt;
}
