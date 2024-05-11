package com.chandimal.auctionApp.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class Bidder extends BidObserver{


    @Override
    protected void updateHighestBid(CompletableFuture<Double> newHighestBid) {


    }
}
