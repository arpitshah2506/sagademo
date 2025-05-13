package com.example.serviceA.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SagaAService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SagaAService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "saga-events", groupId = "service-a-group")
    public void listen(String message) {
        System.out.println("Service A received: " + message);

        // Simulate processing logic
        try {
            Thread.sleep(1000); // pretend doing work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Send next event to Kafka
        kafkaTemplate.send("saga-a-completed", "SAGA_A_COMPLETED");
        System.out.println("Service A published: SAGA_A_COMPLETED");
    }
}
