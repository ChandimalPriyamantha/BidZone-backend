package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyAuctionsRepository extends JpaRepository<Auction,Long> {

    //Get all my auctions providing username
    @Query(nativeQuery = true, value = "select * from auction where user_name=:user_name ")
    List<Object> getMyAuctions(String user_name);
}
