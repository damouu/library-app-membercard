package com.example.demo.kafka.config;

import com.example.demo.dto.UserCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class UserCreatedKafkaConfig extends AbstractKafkaConsumerConfig<UserCreatedEvent> {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> userCreatedKafkaListenerContainerFactory() {
        return containerFactory(UserCreatedEvent.class);
    }
}
