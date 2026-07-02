package com.example.demo.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record Metadata(
        OffsetDateTime timestamp,
        String source_service,
        String event_type,
        UUID event_uuid
) {
}
