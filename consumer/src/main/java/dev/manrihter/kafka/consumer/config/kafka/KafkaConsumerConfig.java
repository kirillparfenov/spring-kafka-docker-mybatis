package dev.manrihter.kafka.consumer.config.kafka;

import dev.manrihter.kafka.consumer.model.domain.KafkaUser;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    private static final String USER_GROUP = "USER_GROUP";
    private static final String USER_CONTAINER_FACTORY = "USER_CONTAINER_FACTORY";
    @Value("${kafka.bootstrap.servers}")
    String servers;

    @Bean
    public ConsumerFactory<String, KafkaUser> consumerFactory() {
        JsonDeserializer<KafkaUser> deserializer = new JsonDeserializer<>(KafkaUser.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, USER_GROUP);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(
                props, new StringDeserializer(), deserializer
        );
    }

    @Bean(USER_CONTAINER_FACTORY)
    public ConcurrentKafkaListenerContainerFactory<String, KafkaUser> registerKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaUser> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
