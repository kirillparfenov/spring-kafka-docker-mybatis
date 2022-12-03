package dev.manrihter.kafka.consumer.service.impl;

import dev.manrihter.kafka.consumer.model.domain.KafkaUser;
import dev.manrihter.kafka.consumer.service.KafkaConsumerService;
import dev.manrihter.kafka.consumer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    private final UserService userService;

    @Autowired
    public KafkaConsumerServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    @KafkaListener(topics = "USER_TOPIC", groupId = "USER_GROUP", containerFactory = "USER_CONTAINER_FACTORY")
    public void userConsumer(@Payload KafkaUser kafkaUser,
                      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                      @Header(KafkaHeaders.OFFSET) String offset) {
        log.info("Partition: {}, Offset: {}", partition, offset);
        userService.saveUser(kafkaUser);
    }
}
