package dev.manrihter.kafka.producer.controller.impl;

import dev.manrihter.kafka.producer.controller.KafkaUserController;
import dev.manrihter.kafka.producer.model.domain.KafkaUser;
import dev.manrihter.kafka.producer.model.request.KafkaUserRequest;
import dev.manrihter.kafka.producer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaUserControllerImpl implements KafkaUserController {

    private final KafkaProducerService producerService;

    @Autowired
    public KafkaUserControllerImpl(final KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @Override
    public void createUser(KafkaUserRequest request) {
        producerService.sendUser(KafkaUser.fromRequest(request));
    }
}
