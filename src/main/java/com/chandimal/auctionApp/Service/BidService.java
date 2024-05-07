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


    private double highestBid = 0;


    public double getHighestBid() {
        return highestBid;
    }

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

                optionalauction=auctionRepository.findById(bidDTO.getAuction_id());

                if (optionalauction.isPresent()) {

                    auction = optionalauction.get();
                   if(auction.getHighest_bid()<bidDTO.getAmount())
                   {
                       auction.setHighest_bid(bidDTO.getAmount());
                       auctionRepository.save(auction);

                       notifyObservers(bidDTO.getAmount());
                   }

                }

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
            optionalauction = auctionRepository.findById(auction_id);
            Double highest_bid = optionalauction.get().getHighest_bid();
            return highest_bid;
        });
    }




//    public List<String> getBiddersOnITem(Long auction_id)
//    {
//            List<Bid> bidList=bidRepository.getBidsOnitem(auction_id);
//            List<String> bidders = null;
//            for(Bid bid:bidList)
//            {
//                bidders.add(bid.getUser_name());
//            }
//       return bidders;
//    }


}