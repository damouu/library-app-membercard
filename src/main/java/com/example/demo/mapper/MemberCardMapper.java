package com.example.demo.mapper;

import com.example.demo.dto.UserCreatedEvent;
import com.example.demo.model.MemberCard;
import org.springframework.stereotype.Component;

@Component
public class MemberCardMapper {

    public MemberCard toEntities(UserCreatedEvent event) {
        return MemberCard.builder().memberCardUUID(event.data().member_card_uuid()).build();
    }
}
