package dev.manrihter.kafka.consumer.service;

import dev.manrihter.kafka.consumer.model.domain.KafkaUser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

public interface KafkaConsumerService {
    void userConsumer(KafkaUser kafkaUser, int partition, String offset);
}
