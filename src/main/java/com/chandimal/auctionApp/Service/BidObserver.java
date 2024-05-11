package com.chandimal.auctionApp.Service;

import java.util.concurrent.CompletableFuture;

public abstract class BidObserver {

    protected  BidSubject bidsubject;
    protected   abstract void updateHighestBid(CompletableFuture<Double> newHighestBid);
}
