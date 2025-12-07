package com.example.demo.service;

import com.example.demo.model.MemberCard;
import com.example.demo.repository.MemberCardRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class MemberCardService {

    private final MemberCardRepository memberCardRepository;


    public void deleteMemberCard(Map<String, String> data) throws ResponseStatusException {
        UUID uuid = UUID.fromString(data.get("memberCardUUID"));
        Optional<MemberCard> memberCard = Optional.ofNullable(memberCardRepository.findMemberCardByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "member card does not exist")));
        memberCard.get().setDeleted_at(LocalDateTime.now());
        memberCardRepository.save(memberCard.get());
        ResponseEntity.status(204).build();
    }

    public ResponseEntity<MemberCard> postMemberCard(Map<String, String> data) throws ResponseStatusException {
        UUID uuid = UUID.fromString(data.get("memberCardUUID"));
        MemberCard memberCard = new MemberCard(uuid);
        memberCard.setCreatedAT(LocalDateTime.now());
        memberCard.setValidUntil(LocalDateTime.now().plusYears(2));
        memberCardRepository.save(memberCard);
        return ResponseEntity.status(204).build();
    }
}
