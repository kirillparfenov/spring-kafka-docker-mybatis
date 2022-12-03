package dev.manrihter.kafka.consumer.model.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class KafkaUser {
    private UUID id;
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
