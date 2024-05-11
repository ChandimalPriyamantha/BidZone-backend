package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyBidsRepository extends JpaRepository<Bid,Integer> {

    //Get all bids of selected user
    @Query(nativeQuery = true, value = "select auction.user_name, auction.name, bid.*, auction.img from bid inner join auction on auction.id= bid.auction_id where bid.user_name=:user_name order by bid.placed_at DESC")
    List<Object> getMyBids(String user_name);


    //Get all bids related to an auction
    @Query(nativeQuery = true, value = "select * from bid where auction_id=:auction_id")
    List<Bid> getBidOnItem(Integer auction_id);

    //Get the highest Bid related to an auction
    @Query(nativeQuery = true,value = "select * from bid where auction_id=:auction_id order by amount DESC, placed_at DESC LIMIT 1;")
    List<Bid> highestBid (Integer auction_id);
}
