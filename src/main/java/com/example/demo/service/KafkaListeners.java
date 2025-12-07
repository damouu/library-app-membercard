package com.example.demo.service;

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

    @KafkaListener(topics = "auth-create-topic", groupId = "groupId")
    void listenerBorrow(Map<String, String> message) {
        memberCardService.postMemberCard(message);
    }

    @KafkaListener(topics = "auth-delete-topic", groupId = "groupId")
    void listenerReturn(Map<String, String> message) {
        memberCardService.deleteMemberCard(message);
    }
}
