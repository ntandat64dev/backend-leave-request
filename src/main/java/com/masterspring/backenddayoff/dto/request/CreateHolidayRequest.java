package com.masterspring.backenddayoff.dto.request;

import com.masterspring.backenddayoff.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateHolidayRequest {
    LocalDate startDate;
    int leaveDays;
    String reason;
    int status = 0;
    long userId;
}
