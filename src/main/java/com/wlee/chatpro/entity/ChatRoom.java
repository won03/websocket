package com.wlee.chatpro.entity;

import com.wlee.chatpro.service.ChatService;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

//챗팅방 추상화
@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    //사용자가 들어왓을때 사용자가 들어왔다는 메세지 또는 사용자가 보내는 메세지를 보내는 액션 핸들링
    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender()+"님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);

    }
    //사용자에대한 메세지들을 오브잭트로 리턴 하여 서비스로 보냄
    private <T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }

}
