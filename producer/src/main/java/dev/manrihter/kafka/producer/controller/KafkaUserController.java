package dev.manrihter.kafka.producer.controller;

import dev.manrihter.kafka.producer.model.request.KafkaUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/users")
public interface KafkaUserController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createUser(@RequestBody KafkaUserRequest request);
}
