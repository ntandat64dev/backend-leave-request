package com.masterspring.backenddayoff.service.impl;

import com.masterspring.backenddayoff.dto.NotificationDto;
import com.masterspring.backenddayoff.entity.Notification;
import com.masterspring.backenddayoff.entity.User;
import com.masterspring.backenddayoff.exception.AppException;
import com.masterspring.backenddayoff.mapper.NotificationMapper;
import com.masterspring.backenddayoff.repository.NotificationRepository;
import com.masterspring.backenddayoff.repository.UserRepository;
import com.masterspring.backenddayoff.service.EmailService;
import com.masterspring.backenddayoff.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;
    private final EmailService emailService;

    @Override
    @Transactional
    public List<NotificationDto> fetchNotificationsByUserId(Long userId) {
        checkUserExist(userId);

        // Mark all notifications as seen
        notificationRepository.markAllAsSeenByUserId(userId);

        return notificationRepository.findAllByUserId(userId).stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    @Override
    public int countUnseenNotificationsByUserId(Long userId) {
        checkUserExist(userId);
        return notificationRepository.countAllByIsNewIsTrueAndUserId(userId);
    }

    @Override
    @Transactional
    public NotificationDto readNotification(Long notificationId, Long userId) {
        checkUserExist(userId);

        // Check if the notification exists.
        var notification = notificationRepository
                .findById(notificationId)
                .orElseThrow(() -> new AppException(400, "Notification not found"));

        // Check if the user has this notification
        if (!Objects.equals(notification.getUser().getId(), userId)) {
            throw new AppException(400, "The user don't has this notification");
        }

        notification.setIsRead(true);
        return notificationMapper.toDto(notification);
    }

    @Override
    @Transactional
    public void readAllNotifications(Long userId) {
        checkUserExist(userId);
        notificationRepository.markAllAsReadByUserId(userId);
    }

    @Override
    @Transactional
    public void postNotification(String message, Long userId) {
        var user = checkUserExist(userId);

        var notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setIsRead(false);
        notification.setIsNew(true);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);

        log.info("Notification sent: userId=%s".formatted(user.getId()));

        try {
            emailService.sendEmail(user.getEmail(), "Leave Request Notification", message);
            log.info("Email sent: userId=%s".formatted(user.getId()));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new AppException(500, "Cannot send email: %s".formatted(e.getMessage()));
        }
    }

    private User checkUserExist(Long userId) {
        // If the user does not exist, then throw error.
        var user = userRepository.findById(userId);
        if (user.isEmpty()) throw new AppException(400, "User not found.");
        return user.get();
    }
}
