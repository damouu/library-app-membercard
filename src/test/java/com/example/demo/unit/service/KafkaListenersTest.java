package com.example.demo.unit.service;

import com.example.demo.service.KafkaListeners;
import com.example.demo.service.MemberCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

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
    @DisplayName("Should delegate borrow event to postMemberCard")
    void listenerBorrow_ShouldCallPostMemberCard() {
        Map<String, String> message = Map.of("uuid", "test");
        kafkaListeners.listenerBorrow(message);
        verify(memberCardService).postMemberCard(message);
    }

    @Test
    @DisplayName("Should delegate delete event to deleteMemberCard")
    void listenerReturn_ShouldCallDeleteMemberCard() {
        Map<String, String> message = Map.of("uuid", "test");
        kafkaListeners.listenerReturn(message);
        verify(memberCardService).deleteMemberCard(message);
    }


}