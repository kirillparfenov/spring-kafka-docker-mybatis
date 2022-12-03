package dev.manrihter.kafka.producer.model.domain;

import dev.manrihter.kafka.producer.model.request.KafkaUserRequest;
import lombok.Data;

import java.util.UUID;

@Data
public class KafkaUser {
    private UUID id;
    private String name;

    public static KafkaUser fromRequest(KafkaUserRequest request) {
        KafkaUser kafkaUser = new KafkaUser();
        kafkaUser.setId(UUID.randomUUID());
        kafkaUser.setName(request.getName());
        return kafkaUser;
    }
}
