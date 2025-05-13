package com.example.serviceB.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SagaOutcomeProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SagaOutcomeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOutcome(String topic, String message) {
        kafkaTemplate.send(topic, message);
        System.out.println("Service B published: " + message + " to topic: " + topic);
    }
}