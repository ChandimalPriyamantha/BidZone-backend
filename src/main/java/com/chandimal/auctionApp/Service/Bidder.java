package com.chandimal.auctionApp.service;

import com.chandimal.auctionApp.dao.NotificationRepo;
import com.chandimal.auctionApp.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class Bidder extends BidObserver{

    private String user_name;


    NotificationRepo notificationRepo;

    public Bidder(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    @Override
    protected void updateHighestBid(Long auction_id, CompletableFuture<Double> newHighestBid) {

            Notification notification = new Notification();
            notification.setBidder_name(user_name);
            notification.setAuction_id(auction_id);
            newHighestBid.thenAccept(highestBid -> {
            notification.setDescription("New highest bid: " + highestBid);});
            notification.setState("Send");
            try {
                notificationRepo.save(notification);
            } catch (Exception e) {
                // Log the exception
                System.err.println("Error saving notification: " + e.getMessage());
            }
        }
    }

