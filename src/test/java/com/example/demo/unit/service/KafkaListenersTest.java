package com.example.demo.unit.service;

import com.example.demo.dto.*;
import com.example.demo.kafka.listener.KafkaListeners;
import com.example.demo.service.MemberCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaListenersTest {

    @Mock
    private MemberCardService memberCardService;

    private KafkaListeners kafkaListeners;

    @BeforeEach
    void setUp() {
        kafkaListeners = new KafkaListeners(memberCardService);
    }

    @Test
    @DisplayName("Should delegate membercard event to postMemberCard")
    void listenermembercard_ShouldCallPostMemberCard() {
        UUID memberCardUuid = UUID.randomUUID();
        Metadata metadata = new Metadata(LocalDateTime.now(), "library-app-authentication-v2", "USER_CREATED", UUID.randomUUID());
        UserCreatedEventData data = new UserCreatedEventData(memberCardUuid);
        UserCreatedEvent event = new UserCreatedEvent(metadata, data);
        kafkaListeners.listenermembercard(event);
        verify(memberCardService).postMemberCard(event);
    }

    @Test
    @DisplayName("Should delegate delete event to deleteMemberCard")
    void listenerReturn_ShouldCallDeleteMemberCard() {
        UUID memberCardUuid = UUID.randomUUID();
        Metadata metadata = new Metadata(LocalDateTime.now(), "library-app-authentication-v2", "USER_CREATED", UUID.randomUUID());
        UserDeletedEventData data = new UserDeletedEventData(memberCardUuid);
        UserDeletedEvent event = new UserDeletedEvent(metadata, data);
        kafkaListeners.listenerReturn(event);
        verify(memberCardService).deleteMemberCard(event);
    }


}