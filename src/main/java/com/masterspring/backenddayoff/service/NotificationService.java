package com.masterspring.backenddayoff.service;

import com.masterspring.backenddayoff.dto.NotificationDto;

import java.util.List;

public interface NotificationService {

    List<NotificationDto> fetchNotificationsByUserId(Long userId);

    int countUnseenNotificationsByUserId(Long userId);

    NotificationDto readNotification(Long notificationId, Long userId);

    void readAllNotifications(Long userId);

    void postNotification(String message, Long userId);
}
