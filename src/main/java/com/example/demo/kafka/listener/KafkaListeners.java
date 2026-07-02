package com.example.demo.kafka.listener;

import com.example.demo.dto.UserCreatedEvent;
import com.example.demo.dto.UserDeletedEvent;
import com.example.demo.service.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaListeners {

    private final MemberCardService memberCardService;

    @Autowired
    public KafkaListeners(MemberCardService memberCardService) {
        this.memberCardService = memberCardService;
    }

    @KafkaListener(topics = "auth-create-topic", groupId = "membercard-group-1", containerFactory = "userCreatedKafkaListenerContainerFactory")
    public void listenermembercard(UserCreatedEvent event) {
        memberCardService.postMemberCard(event);
    }

    @KafkaListener(topics = "auth-delete-topic", groupId = "membercard-group-1", containerFactory = "userDeletedKafkaListenerContainerFactory")
    public void listenerReturn(UserDeletedEvent event) {
        memberCardService.deleteMemberCard(event);
    }
}
