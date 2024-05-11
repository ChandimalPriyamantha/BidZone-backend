package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

   // Page<Auction> findByTitleContaining(@RequestParam("name") String name, Pageable pageable);

   // Page<Auction> findByCategory(@RequestParam("category") String category, Pageable pageable);


}
