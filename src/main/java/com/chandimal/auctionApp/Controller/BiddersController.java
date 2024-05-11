package com.chandimal.auctionApp.controller;

import com.chandimal.auctionApp.service.BidObserver;

import java.util.concurrent.CompletableFuture;

public class BiddersController extends BidObserver {


    @Override
    protected void updateHighestBid(CompletableFuture<Double> newHighestBid) {

    }
}
