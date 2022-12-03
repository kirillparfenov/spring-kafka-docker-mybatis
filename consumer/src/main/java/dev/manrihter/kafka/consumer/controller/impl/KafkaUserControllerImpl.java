package dev.manrihter.kafka.consumer.controller.impl;

import dev.manrihter.kafka.consumer.controller.KafkaUserController;
import dev.manrihter.kafka.consumer.model.domain.KafkaUser;
import dev.manrihter.kafka.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaUserControllerImpl implements KafkaUserController {
    private final UserService userService;

    @Autowired
    public KafkaUserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<KafkaUser> getAllUsers() {
        return userService.getAllKafkaUsers();
    }
}
