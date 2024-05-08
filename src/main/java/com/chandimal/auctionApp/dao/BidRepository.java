package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BidRepository extends JpaRepository<Bid,Integer> {

    @Query(nativeQuery = true, value="select max(amount) from bid where auction_id=:auction_id")
    double getHighestBid(@Param("auction_id")long auction_id);

}
