package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Integer> {
}
