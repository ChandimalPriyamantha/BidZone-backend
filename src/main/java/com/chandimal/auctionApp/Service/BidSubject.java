package com.chandimal.auctionApp.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public  class BidSubject {


     List<BidObserver> observers = new ArrayList<>();

     private Long auction_id;

    public BidSubject(Long auction_id) {
        this.auction_id = auction_id;
    }

    public void addObserver(BidObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BidObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Long auction_id, CompletableFuture<Double> highestBid) {

        for (BidObserver observer : observers) {
            observer.updateHighestBid(auction_id,highestBid);
        }
    }
}
