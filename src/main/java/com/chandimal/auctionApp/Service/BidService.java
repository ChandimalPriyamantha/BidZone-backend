package com.chandimal.auctionApp.Service;

import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Util.VarList;
import com.chandimal.auctionApp.dao.AuctionRepository;
import com.chandimal.auctionApp.dao.BidRepository;
import com.chandimal.auctionApp.dao.NotificationRepo;
import com.chandimal.auctionApp.entity.Auction;
import com.chandimal.auctionApp.entity.Bid;
import com.chandimal.auctionApp.entity.Notification;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class BidService  {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificationRepo notificationRepo;

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
                BidSubject newBid = new BidSubject(bidDTO.getAuction_id());
                List<Bid> bids = bidRepository.getBidders(bidDTO.getAuction_id(),bidDTO.getUser_name());

                for (Bid ele : bids) {
                    Bidder newBidder = new Bidder(notificationRepo);
                    newBidder.setUser_name(ele.getUser_name());
                    newBid.addObserver(newBidder);
                }

                // Asynchronously retrieve the highest bid for the auction
                CompletableFuture<Double> highest_bid = getHighestBid(bidDTO.getAuction_id());
                System.out.println(highest_bid);

               // Perform actions when the highest bid retrieval is complete
               highest_bid.thenAccept(highestBidAmount -> {
                   // Compare the bid amount with the highest bid outside the callback
                    if (bidDTO.getAmount() == highestBidAmount) {


                        try
                        {
                            notificationRepo.deletePreviousNotification(bidDTO.getAuction_id());
                        }catch (Exception e)
                        {
                            System.out.println("No data");
                        }

                        // Notify observers if the bid amount is greater than the highest bid
                        newBid.notifyObservers(bidDTO.getAuction_id(), highest_bid);
                   }
               });

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

    public ResponseDTO getNotification(String user_name,Long auction_id)
    {
        ResponseDTO responseDTO=new ResponseDTO();
        try
        {

            Notification notification=notificationRepo.getHighestBidNotification(user_name,auction_id);
            responseDTO.setCode(VarList.RIP_SUCCESS);
            responseDTO.setContent(notification);
            responseDTO.setMessage("successful");

        }catch (Exception e)
        {
            responseDTO.setCode(VarList.RIP_ERROR);
            responseDTO.setContent(null);
            responseDTO.setMessage("unsuccessful");
        }
        return responseDTO;
    }
}
