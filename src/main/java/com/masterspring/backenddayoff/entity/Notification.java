package com.masterspring.backenddayoff.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Boolean isRead;

    private Boolean isNew;

    private LocalDateTime timestamp;

    @ManyToOne
    private User user;
}
