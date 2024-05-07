package com.chandimal.auctionApp.service;


import com.chandimal.auctionApp.dao.AuctionRepository;
import com.chandimal.auctionApp.entity.Auction;
import com.chandimal.auctionApp.requestmodels.AddAuctionRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuctionService {

    private AuctionRepository auctionRepository;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    // To save new auction details.
    public void addAuction(AddAuctionRequest addAuctionRequest){

        Auction auction = new Auction();
        auction.setClosingTime(addAuctionRequest.getClosingTime());
        auction.setCreatedTime(addAuctionRequest.getCreatedTime());
        auction.setStartingPrice(addAuctionRequest.getStartingPrice());
        auction.setName(addAuctionRequest.getName());
        auction.setDescription(addAuctionRequest.getDescription());
        auction.setCategory(addAuctionRequest.getCategory());
        auction.setImg(addAuctionRequest.getImg());
        auction.setUserName(addAuctionRequest.getUserName());
        auctionRepository.save(auction);

    }
}
