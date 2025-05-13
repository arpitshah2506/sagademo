package com.example.serviceB.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.serviceB.producer.SagaOutcomeProducer;

@Service
public class SagaACompletedListener {

    private final SagaOutcomeProducer producer;

    public SagaACompletedListener(SagaOutcomeProducer producer) {
        this.producer = producer;
    }

    @KafkaListener(topics = "saga-a-completed", groupId = "service-b-group")
    public void onMessage(String message) {
        System.out.println("Service B received: " + message);

        // Simulate logic — here we'll fail if message contains "FAIL"
        if (message.contains("FAIL")) {
            producer.sendOutcome("saga-failure", "SAGA_FAILED_AT_B");
        } else {
            producer.sendOutcome("saga-success", "SAGA_SUCCESS");
        }
    }
}