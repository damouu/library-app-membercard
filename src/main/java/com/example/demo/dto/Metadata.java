package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record Metadata(
        LocalDateTime timestamp,
        String source_service,
        String event_type,
        UUID event_uuid
) {
}
