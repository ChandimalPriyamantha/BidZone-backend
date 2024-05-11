package com.chandimal.auctionApp.Controller;

import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Service.MyBidsService;
import com.chandimal.auctionApp.Util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/MyBids")
public class MyBidsController {

    @Autowired
    MyBidsService myBidsService;


    //Get all bids of selected user
    @GetMapping("/getMyBids/{user_name}")
    public List<Object> getMyBids(@PathVariable String user_name) throws ExecutionException, InterruptedException {
        Future<List<Object>> future = myBidsService.getMyBids(user_name);
        return future.get();            // Waits for the async operation to complete
    }


    //update Bid
    @PutMapping("/updateBid")
    public String updateBid(@RequestBody BidDTO bidDTO) throws ExecutionException, InterruptedException {
        Future<String> future = myBidsService.updateBid(bidDTO);
        return future.get();            // Waits for the async operation to complete
    }

    //Get bid details for selected auction
    @GetMapping("/getBidOnItem/{auction_id}")
    public List<BidDTO> getBidOnItem(@PathVariable Integer auction_id) throws ExecutionException, InterruptedException{
        Future<List<BidDTO>> future = myBidsService.getBidOnItem(auction_id);
        return future.get();
    }


    //Delete selected bid
    @DeleteMapping("/deleteBid")
    public void deleteBid(@RequestBody BidDTO bidDTO){
        myBidsService.deleteBid(bidDTO);
    }

    //Get the highest Bid related to an auction
    @GetMapping("/highestBid/{auction_id}")
    public List<BidDTO> highestBid(@PathVariable Integer auction_id) throws ExecutionException, InterruptedException {
        Future<List<BidDTO>> future = myBidsService.highestBid(auction_id);
        return future.get();

    }
}
