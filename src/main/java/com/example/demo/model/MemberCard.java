package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "member_card")
@Table(name = "member_card", uniqueConstraints = @UniqueConstraint(columnNames = {"member_card_uuid", "member_card_uuid"}, name = "member_card_uuid_unique"))
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MemberCard implements Serializable {
    @Id
    @Column(unique = true, updatable = false, nullable = false)
    @SequenceGenerator(name = "memberCard_sequence", allocationSize = 1, sequenceName = "memberCard_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberCard_sequence")
    @Getter(onMethod = @__(@JsonIgnore))
    @Setter
    private Integer id;

    @Column(name = "member_card_uuid", columnDefinition = "UUID", nullable = false)
    @Getter
    @Setter
    private UUID memberCardUUID;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "valid_until", columnDefinition = "date", nullable = false)
    @Getter
    @Setter
    private LocalDateTime validUntil;

    @Column(name = "deleted_at", columnDefinition = "timestamp")
    @Getter
    @Setter
    private LocalDateTime deleted_at;
}
