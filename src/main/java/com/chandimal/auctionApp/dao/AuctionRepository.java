package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.entity.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

   // Page<Auction> findByTitleContaining(@RequestParam("name") String name, Pageable pageable);

   // Page<Auction> findByCategory(@RequestParam("category") String category, Pageable pageable);
}
