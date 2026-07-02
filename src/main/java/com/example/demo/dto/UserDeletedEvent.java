package com.example.demo.dto;

public record UserDeletedEvent(
        Metadata metadata,
        UserDeletedEventData data
) {
}
