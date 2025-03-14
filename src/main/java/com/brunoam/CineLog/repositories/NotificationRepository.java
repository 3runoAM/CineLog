package com.brunoam.CineLog.repositories;

import com.brunoam.CineLog.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    //
}
