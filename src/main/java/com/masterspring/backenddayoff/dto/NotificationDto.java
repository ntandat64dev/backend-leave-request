package com.masterspring.backenddayoff.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {

    private Long id;

    private Long userId;

    private String message;

    private Boolean isRead;

    private Boolean isNew;

    private LocalDateTime timestamp;
}
