package com.masterspring.backenddayoff.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class LeaveRequestPost {
    @NotNull(message = "userId cannot be null.")
    private Long userId;

    @NotNull(message = "startDate cannot be null.")
    @FutureOrPresent(message = "Start date must be in the future")
    private LocalDate startDate;

    @NotNull(message = "endDate cannot be null.")
    @FutureOrPresent(message = "End date must be in the future")
    private LocalDate endDate;

    @NotNull
    private int validDays;

    @NotNull(message = "createdAt cannot be null.")
    private LocalDateTime createdAt;

    @NotNull(message = "reason cannot be null.")
    private String reason;
}