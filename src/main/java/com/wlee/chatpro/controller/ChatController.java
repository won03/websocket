package com.wlee.chatpro.controller;

import com.wlee.chatpro.entity.ChatRoom;
import com.wlee.chatpro.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom(){
        return chatService.findAllRoom();
    }
}
