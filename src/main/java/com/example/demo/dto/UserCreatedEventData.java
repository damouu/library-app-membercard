package com.example.demo.dto;

import java.util.UUID;

public record UserCreatedEventData(
        UUID member_card_uuid
) {
}
