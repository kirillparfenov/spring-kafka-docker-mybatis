package dev.manrihter.kafka.consumer.mappers;

import dev.manrihter.kafka.consumer.model.domain.KafkaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    void saveUser(@Param("kafkaUser") KafkaUser kafkaUser);

    List<KafkaUser> getAllUsers();
}
