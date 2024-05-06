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

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/MyBids")
public class MyBidsController {

    @Autowired
    MyBidsService myBidsService;


    //Get all bids of selected user
    @GetMapping("/getMyBids/{user_name}")
    public List<Object> getMyBids(@PathVariable String user_name){
        return myBidsService.getMyBids(user_name);
    }


    //update Bid
    @PutMapping("/updateBid")
    public String updateBid(@RequestBody BidDTO bidDTO){
        return myBidsService.updateBid(bidDTO);
    }

    @DeleteMapping("/deleteBid")
    public void deleteBid(@RequestBody BidDTO bidDTO){
        myBidsService.deleteBid(bidDTO);
    }
}
