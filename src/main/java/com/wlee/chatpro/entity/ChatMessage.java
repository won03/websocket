package com.wlee.chatpro.entity;

import lombok.Data;

//채팅 데이터 와 챗팅 방의 아이디
@Data
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
