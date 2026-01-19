package com.example.demo.unit.service;

import com.example.demo.model.MemberCard;
import com.example.demo.repository.MemberCardRepository;
import com.example.demo.service.MemberCardService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberCardServiceTest {

    @Mock
    private MemberCardRepository memberCardRepository;

    @InjectMocks
    private MemberCardService memberCardService;

    private MemberCard memberCard;

    @BeforeEach
    void setUp() {
        memberCard = Instancio.create(MemberCard.class);
    }

    @Test
    void postMemberCard() {
        UUID uuid = UUID.randomUUID();
        Map<String, String> data = new HashMap<>();
        data.put("memberCardUUID", uuid.toString());
        memberCardService.postMemberCard(data);
        verify(memberCardRepository, times(1)).save(any());
    }

    @Test
    void deleteMemberCard_exception() {
        UUID uuid = UUID.randomUUID();
        Map<String, String> data = new HashMap<>();
        data.put("memberCardUUID", uuid.toString());
        when(memberCardRepository.findMemberCardByUuid(uuid)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            memberCardService.deleteMemberCard(data);
        });
        verify(memberCardRepository, times(1)).findMemberCardByUuid(uuid);
    }

    @Test
    void deleteMemberCard() {
        UUID uuid = UUID.randomUUID();
        Map<String, String> data = new HashMap<>();
        data.put("memberCardUUID", uuid.toString());
        when(memberCardRepository.findMemberCardByUuid(uuid)).thenReturn(Optional.ofNullable(memberCard));
        memberCardService.deleteMemberCard(data);
        verify(memberCardRepository, times(1)).save(any());
    }
}