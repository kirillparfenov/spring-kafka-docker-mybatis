package dev.manrihter.kafka.producer.service;

import dev.manrihter.kafka.producer.model.domain.KafkaUser;

public interface KafkaProducerService {
    void sendUser(KafkaUser kafkaUser);
}
