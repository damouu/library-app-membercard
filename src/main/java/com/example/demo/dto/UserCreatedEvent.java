package com.example.demo.dto;

public record UserCreatedEvent(
        Metadata metadata,
        UserCreatedEventData data
) {
}
