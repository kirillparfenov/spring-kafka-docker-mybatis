package dev.manrihter.kafka.producer.service.impl;

import dev.manrihter.kafka.producer.config.kafka.KafkaTopics;
import dev.manrihter.kafka.producer.model.domain.KafkaUser;
import dev.manrihter.kafka.producer.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

    private final KafkaTemplate<String, KafkaUser> kafkaTemplate;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, KafkaUser> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendUser(KafkaUser kafkaUser) {
        var future = kafkaTemplate.send(KafkaTopics.USER_TOPIC.getValue(), kafkaUser);
        future.addCallback(new ListenableFutureCallback<SendResult<String, KafkaUser>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Exception while push to topic: {}", KafkaTopics.USER_TOPIC.getValue());
            }

            @Override
            public void onSuccess(SendResult<String, KafkaUser> result) {
                log.info("Sent to topic: {}, partition: {}, offset: {}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset()
                );
            }
        });
    }
}
