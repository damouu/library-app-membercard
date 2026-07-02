package com.example.demo.service;

import com.example.demo.dto.UserCreatedEvent;
import com.example.demo.dto.UserDeletedEvent;
import com.example.demo.mapper.MemberCardMapper;
import com.example.demo.model.MemberCard;
import com.example.demo.policy.ValidityPolicy;
import com.example.demo.repository.MemberCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberCardService {

    private final MemberCardRepository memberCardRepository;

    private final MemberCardMapper memberCardMapper;

    private final ValidityPolicy validityPolicy;

    public void deleteMemberCard(UserDeletedEvent event) {
        Optional<MemberCard> memberCard = Optional.ofNullable(memberCardRepository.findMemberCardByUuid(event.data().member_card_uuid()));
        memberCard.get().setDeleted_at(LocalDateTime.now());
        memberCardRepository.save(memberCard.get());
    }

    public void postMemberCard(UserCreatedEvent event) {
        MemberCard memberCard = memberCardMapper.toEntities(event);
        LocalDateTime validUntil = validityPolicy.calculateExpiration(event);
        memberCard.setValidUntil(validUntil);
        memberCardRepository.save(memberCard);
    }
}
