package dev.manrihter.kafka.consumer.service.impl;

import dev.manrihter.kafka.consumer.mappers.UserMapper;
import dev.manrihter.kafka.consumer.model.domain.KafkaUser;
import dev.manrihter.kafka.consumer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static dev.manrihter.kafka.consumer.config.db.DataBaseConfig.TRANSACTIONS;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(transactionManager = TRANSACTIONS, rollbackFor = Exception.class)
    public void saveUser(KafkaUser kafkaUser) {
        try {
            userMapper.saveUser(kafkaUser);
            log.info("User successfully saved: [{}]", kafkaUser);
        } catch (Exception e) {
            log.error("Exception while user saving: [{}]. Exception: [{}]", kafkaUser, e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<KafkaUser> getAllKafkaUsers() {
        return userMapper.getAllUsers();
    }
}
