package dev.manrihter.kafka.consumer.service;

import dev.manrihter.kafka.consumer.model.domain.KafkaUser;

import java.util.List;

public interface UserService {
    void saveUser(KafkaUser kafkaUser);

    List<KafkaUser> getAllKafkaUsers();
}
