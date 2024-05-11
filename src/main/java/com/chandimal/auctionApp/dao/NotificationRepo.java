package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {

    @Query(nativeQuery = true, value = "select * from notification where user_name=:user_name and auction_id=:auction_id")
    Notification getHighestBidNotification(@Param("user_name") String user_name, @Param("auction_id") int auction_id);
}
