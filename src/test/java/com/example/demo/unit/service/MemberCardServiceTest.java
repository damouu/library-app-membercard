package com.example.demo.unit.service;

import com.example.demo.dto.*;
import com.example.demo.mapper.MemberCardMapper;
import com.example.demo.model.MemberCard;
import com.example.demo.policy.ValidityPolicy;
import com.example.demo.repository.MemberCardRepository;
import com.example.demo.service.MemberCardService;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberCardServiceTest {

    @Mock
    private MemberCardRepository memberCardRepository;

    @Mock
    private MemberCardMapper memberCardMapper;

    @Mock
    private ValidityPolicy validityPolicy;

    @InjectMocks
    private MemberCardService memberCardService;

    private MemberCard memberCard;

    @BeforeEach
    void setUp() {
        memberCard = Instancio.create(MemberCard.class);
    }

    @Test
    void postMemberCard() {
        UUID memberCardUuid = UUID.randomUUID();
        Metadata metadata = new Metadata(OffsetDateTime.now(), "library-app-authentication-v2", "USER_CREATED", UUID.randomUUID());
        UserCreatedEventData data = new UserCreatedEventData(memberCardUuid);
        UserCreatedEvent event = new UserCreatedEvent(metadata, data);
        when(memberCardMapper.toEntities(event)).thenReturn(MemberCard.builder().build());
        when(validityPolicy.calculateExpiration(event)).thenReturn(LocalDateTime.now().plusMinutes(2));
        memberCardService.postMemberCard(event);
        verify(memberCardRepository, times(1)).save(any());
        verify(memberCardMapper, times(1)).toEntities(any());
        verify(validityPolicy, times(1)).calculateExpiration(event);
    }

    @Test
    void deleteMemberCard() {
        UUID memberCardUuid = UUID.randomUUID();
        Metadata metadata = new Metadata(OffsetDateTime.now(), "library-app-authentication-v2", "USER_CREATED", UUID.randomUUID());
        UserDeletedEventData data = new UserDeletedEventData(memberCardUuid);
        UserDeletedEvent event = new UserDeletedEvent(metadata, data);
        when(memberCardRepository.findMemberCardByUuid(memberCardUuid)).thenReturn(memberCard);
        memberCardService.deleteMemberCard(event);
        verify(memberCardRepository, times(1)).save(any());
    }
}