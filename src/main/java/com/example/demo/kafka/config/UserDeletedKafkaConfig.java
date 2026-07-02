package com.example.demo.kafka.config;

import com.example.demo.dto.UserDeletedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class UserDeletedKafkaConfig extends AbstractKafkaConsumerConfig<UserDeletedEvent> {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserDeletedEvent> userDeletedKafkaListenerContainerFactory() {
        return containerFactory(UserDeletedEvent.class);
    }
}
