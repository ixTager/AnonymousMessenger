package com.anonchat.anonymousmessenger.controller;

import com.anonchat.anonymousmessenger.entity.Dialog;
import com.anonchat.anonymousmessenger.repository.DialogRepository;
import com.anonchat.anonymousmessenger.service.MessageCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

@Controller
@RequiredArgsConstructor
public class PagesController {
    private final DialogRepository dialogRepository;
    private final MessageCacheService messageCacheService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("chats", dialogRepository.findAll());
        return "index";
    }

    @GetMapping("/chat/{dialogId}")
    public String chat(@PathVariable String dialogId,
                       @RequestParam(required = false, defaultValue = "Аноним") String nickname,
                       Model model) {
        if (!dialogRepository.existsById(dialogId)) {
            dialogRepository.save(Dialog.builder()
                            .id(dialogId)
                            .createdAt(Instant.now())
                            .creatorNickname(nickname)
                            .lastMessage(messageCacheService.getLastMessage(dialogId))
                            .build());
        }

        model.addAttribute("dialogId", dialogId);
        return "chat";
    }

}
