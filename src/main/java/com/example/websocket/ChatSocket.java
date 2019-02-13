package com.example.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

public class ChatSocket implements WebSocketHandler {

    private Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession)
            throws Exception {
        sessions.add(webSocketSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus)
            throws Exception {
        sessions.remove(webSocketSession);

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
            throws Exception {
        for (WebSocketSession session : sessions) {
            session.sendMessage(webSocketMessage);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable)
            throws Exception {
        sessions.remove(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}




