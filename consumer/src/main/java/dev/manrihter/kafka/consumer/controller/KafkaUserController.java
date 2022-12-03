package dev.manrihter.kafka.consumer.controller;

import dev.manrihter.kafka.consumer.model.domain.KafkaUser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/users")
public interface KafkaUserController {

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<KafkaUser> getAllUsers();
}
