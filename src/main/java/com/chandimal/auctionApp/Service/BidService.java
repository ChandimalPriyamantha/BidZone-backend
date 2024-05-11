package com.chandimal.auctionApp.Service;

import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Util.VarList;
import com.chandimal.auctionApp.dao.AuctionRepository;
import com.chandimal.auctionApp.dao.BidRepository;
import com.chandimal.auctionApp.entity.Auction;
import com.chandimal.auctionApp.entity.Bid;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Transactional
@Service
@Data
public class BidService extends BidSubject{


    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDTO responseDTO;



    private Optional<Auction> optionalauction;
    private Auction auction;


    public void addObserver(BidObserver observer) {
        addObserver(observer);
    }

    public void removeObserver(BidObserver observer) {
        removeObserver(observer);
    }

    @Async
    public CompletableFuture<ResponseDTO> placeBid(BidDTO bidDTO) {
        return CompletableFuture.supplyAsync(() -> {

            try {
                bidRepository.save(modelMapper.map(bidDTO,Bid.class));

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


            optionalauction=auctionRepository.findById(auction_id);

            if (optionalauction.isPresent()) {

                auction = optionalauction.get();

                auction.setHighest_bid(highest_bid);
                auctionRepository.save(auction);

                notifyObservers(highest_bid);


            }

            return highest_bid;
        });
    }







}