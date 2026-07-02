package com.example.demo.dto;

import java.util.UUID;

public record UserDeletedEventData(
        UUID member_card_uuid
) {
}
