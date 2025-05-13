package com.example.master.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.master.config.SagaWebSocketHandler;

@Service
public class KafkaConsumerService {
	
	private final SagaWebSocketHandler webSocketHandler;

    public KafkaConsumerService(SagaWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

	@KafkaListener(topics = "saga-success", groupId = "service-status-group")
    public void onMessage(String message) {
        System.out.println("Master Service received: " + message);
        webSocketHandler.broadcast(message);
        // Simulate logic — here we'll fail if message contains "FAIL"
    }
}
