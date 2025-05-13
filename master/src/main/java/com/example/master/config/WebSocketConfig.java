package com.example.master.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final SagaWebSocketHandler sagaWebSocketHandler;

    public WebSocketConfig(SagaWebSocketHandler sagaWebSocketHandler) {
        this.sagaWebSocketHandler = sagaWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(sagaWebSocketHandler, "/ws/saga")
                .setAllowedOrigins("*");
    }
}

