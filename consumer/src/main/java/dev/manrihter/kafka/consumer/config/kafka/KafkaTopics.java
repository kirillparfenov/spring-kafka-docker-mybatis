package dev.manrihter.kafka.consumer.config.kafka;

public enum KafkaTopics {
    USER_TOPIC("USER_TOPIC");

    private String topic;
    KafkaTopics(String topic) {
        this.topic = topic;
    }

    public String getValue() {
        return this.topic;
    }
}
