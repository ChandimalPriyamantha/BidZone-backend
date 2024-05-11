package com.chandimal.auctionApp.service;

import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Util.VarList;
import com.chandimal.auctionApp.dao.AuctionRepository;
import com.chandimal.auctionApp.dao.BidRepository;
import com.chandimal.auctionApp.entity.Auction;
import com.chandimal.auctionApp.entity.Bid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BidService extends BidSubject {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ModelMapper modelMapper;



    private Optional<Auction> optionalAuction;

    private Auction auction;

    @Async
    public CompletableFuture<ResponseDTO> placeBid(BidDTO bidDTO) {
        return CompletableFuture.supplyAsync(() -> {
            ResponseDTO responseDTO = new ResponseDTO();
            try {
                Bid bid = modelMapper.map(bidDTO, Bid.class);
                bidRepository.save(bid);
                responseDTO.setCode(VarList.RIP_SUCCESS);
                responseDTO.setContent(bidDTO);
                responseDTO.setMessage("Successfully added Bid");




            } catch (Exception e) {
                System.out.println(e.getMessage());
                responseDTO.setCode(VarList.RIP_ERROR);
                responseDTO.setContent(bidDTO);
                responseDTO.setMessage("Error adding Bid");
            }
            return responseDTO;
        });
    }

    public CompletableFuture<Double> getHighestBid(long auction_id) {
        return CompletableFuture.supplyAsync(() -> {
            Double highest_bid = bidRepository.getHighestBid(auction_id);
            try {
                optionalAuction = auctionRepository.findById(auction_id);
                if (optionalAuction.isPresent()) {
                    auction = optionalAuction.get();
                    auction.setHighest_bid(highest_bid);
                    auctionRepository.save(auction);
                }
            } catch (NullPointerException e) {
                return 0.0;
            }
            return highest_bid;
        });
    }
}
