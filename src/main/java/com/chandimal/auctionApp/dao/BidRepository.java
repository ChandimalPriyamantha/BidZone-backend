package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid,Integer> {

    @Query(nativeQuery = true, value="select max(amount) from bid where auction_id=:auction_id")
    Double getHighestBid(@Param("auction_id")long auction_id);

    @Query(nativeQuery = true, value = "select distinct * from bid where auction_id=:auction_id and user_name!=:user_name group by user_name")
    List<Bid> getBidders(@Param("auction_id")long auction_id,@Param("user_name") String user_name);



}