package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid,Integer> {

    @Query(nativeQuery = true,value ="select * from bid where auction_id=:auction_id order by amount desc")
    List<Bid> getBidsOnitem(@Param("auction_id")long auction_id);

}
