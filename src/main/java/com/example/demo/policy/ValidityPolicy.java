package com.example.demo.policy;

import com.example.demo.dto.UserCreatedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidityPolicy {

    public LocalDateTime calculateExpiration(UserCreatedEvent event) {
        return event.metadata().timestamp().plusYears(2).toLocalDateTime();
    }
}
