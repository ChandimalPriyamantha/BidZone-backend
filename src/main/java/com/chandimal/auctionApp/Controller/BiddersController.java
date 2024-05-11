package com.chandimal.auctionApp.Controller;

import com.chandimal.auctionApp.Service.BidObserver;

import java.util.concurrent.CompletableFuture;

public class BiddersController extends BidObserver {


    @Override
    protected void updateHighestBid(CompletableFuture<Double> newHighestBid) {

    }
}
