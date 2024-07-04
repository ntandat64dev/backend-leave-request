package com.masterspring.backenddayoff.repository;

import com.masterspring.backenddayoff.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserId(Long userId);

    int countAllByIsNewIsTrueAndUserId(Long userId);

    @Query("UPDATE Notification n SET n.isRead = TRUE WHERE n.user.id = :userId")
    @Modifying
    void markAllAsReadByUserId(Long userId);

    @Query("UPDATE Notification n SET n.isNew = TRUE WHERE n.user.id = :userId")
    @Modifying
    void markAllAsSeenByUserId(Long userId);
}
