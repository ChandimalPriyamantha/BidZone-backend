package com.chandimal.auctionApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public abstract class BidSubject {

     List<BidObserver> observers = new ArrayList<>();



    public void addObserver(BidObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BidObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(CompletableFuture<Double> highestBid) {
        for (BidObserver observer : observers) {
            observer.updateHighestBid(highestBid);
        }
    }
}
