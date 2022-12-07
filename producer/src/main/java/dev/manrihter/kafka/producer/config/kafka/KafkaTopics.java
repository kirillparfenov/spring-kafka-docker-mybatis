package dev.manrihter.kafka.producer.config.kafka;

public enum KafkaTopics {
    USER_TOPIC("USER_TOPIC");

    private final String topic;
    KafkaTopics(String topic) {
        this.topic = topic;
    }

    public String getValue() {
        return this.topic;
    }
}
