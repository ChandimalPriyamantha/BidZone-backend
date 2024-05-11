package com.chandimal.auctionApp.service;

import com.chandimal.auctionApp.DTO.AuctionDTO;
import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.dao.MyAuctionsRepository;
import com.chandimal.auctionApp.entity.Auction;
import com.chandimal.auctionApp.entity.Bid;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
@Transactional
public class MyAuctionsService {
    @Autowired
    MyAuctionsRepository myAuctionsRepository;
    @Autowired
    ModelMapper mp;

    //Get all my auctions
    @Async
    public Future<List<Object>> getMyAuctions(String user_name){
        List <Object> auctions = myAuctionsRepository.getMyAuctions(user_name);
        return new AsyncResult<>(auctions);
    }

    //Update Auction
    @Async
    public Future<String> updateAuction(AuctionDTO auctionDTO){
        if(myAuctionsRepository.existsById(auctionDTO.getId())){

            myAuctionsRepository.save(mp.map(auctionDTO, Auction.class));
            return new AsyncResult<>("Updated successfully");
        }else{
            return new AsyncResult<>("Matching data is not found");
        }

    }

    //delete Auction
    @Async
    public void deleteAuction (AuctionDTO auctionDTO){
        if(myAuctionsRepository.existsById(auctionDTO.getId())){
            myAuctionsRepository.delete(mp.map(auctionDTO,Auction.class));
        }

    }
}
