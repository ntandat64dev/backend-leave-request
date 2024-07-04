package com.masterspring.backenddayoff.controller;

import com.masterspring.backenddayoff.dto.NotificationDto;
import com.masterspring.backenddayoff.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationDto>> getNotifications(@PathVariable Long userId) {
        var response = notificationService.fetchNotificationsByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unseen/{userId}")
    public ResponseEntity<Integer> countUnseenNotifications(@PathVariable Long userId) {
        var response = notificationService.countUnseenNotificationsByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/read")
    public ResponseEntity<NotificationDto> readNotification(
            Long notificationId,
            Long userId
    ) {
        var response = notificationService.readNotification(notificationId, userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/read/all")
    public ResponseEntity<?> readAllNotifications(Long userId) {
        notificationService.readAllNotifications(userId);
        return ResponseEntity.noContent().build();
    }
}
