package com.chandimal.auctionApp.controller;

import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.service.BidService;
import com.chandimal.auctionApp.Util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/Bid")
public class BidController {

    @Autowired
    BidService bidService;

    @PostMapping("placeBid")
    public ResponseEntity placeBid(@RequestBody BidDTO bidDTO) throws  ExecutionException, InterruptedException {


        CompletableFuture<ResponseDTO> futureresponse=bidService.placeBid(bidDTO);
        ResponseDTO response=futureresponse.get();
        if(response.getCode().equals(VarList.RIP_SUCCESS))
        {
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }

    }

   @GetMapping("/getHighestBid/{auction_id}")
    public ResponseEntity getHighestBid(@PathVariable Long auction_id) throws ExecutionException, InterruptedException {
       CompletableFuture<Double> highest_bid=bidService.getHighestBid(auction_id);
       return new ResponseEntity( highest_bid.get(),HttpStatus.OK);
   }


}
