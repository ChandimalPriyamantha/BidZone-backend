package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyBidsRepository extends JpaRepository<Bid,Integer> {

    //Get all bids of selected user
    @Query(nativeQuery = true, value = "select auction.user_name, auction.name, bid.* from bid inner join auction on auction.id= bid.auction_id where bid.user_name=:user_name")
    List<Object> getMyBids(String user_name);
}
