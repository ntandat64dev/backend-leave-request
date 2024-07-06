package com.masterspring.backenddayoff.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "holidays")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`start_date`", nullable = false)
    private LocalDate startDate;

    @Column(name = "`end_date`", nullable = false)
    private LocalDate endDate;

    @Column(name = "`reason`", nullable = false)
    private String reason;

    /**
     * 0 - Accepted
     * 1 - Rejected
     * 2 - Waiting
     */
    @Column(name = "`status`", nullable = false)
    private Integer status;

    @Column(name = "`created_at`", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "`user_id`")
    private User user;
}
