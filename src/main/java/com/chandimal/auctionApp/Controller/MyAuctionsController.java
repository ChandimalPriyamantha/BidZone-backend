package com.chandimal.auctionApp.Controller;

import com.chandimal.auctionApp.DTO.AuctionDTO;
import com.chandimal.auctionApp.Service.MyAuctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/MyAuctions")

public class MyAuctionsController {
    @Autowired
    MyAuctionsService myAuctionsService;


    //Get all auctions of selected user
    @GetMapping("/getMyAuctions/{user_name}")
    public List<Object> getMyAuctions(@PathVariable String user_name)throws ExecutionException, InterruptedException {
        Future<List<Object>> future = myAuctionsService.getMyAuctions(user_name);
        return future.get();    // Waits for the async operation to complete
    }

    @PutMapping("/updateAuction")
    public String updateAuction(@RequestBody AuctionDTO auctionDTO) throws ExecutionException, InterruptedException {
        Future<String> future = myAuctionsService.updateAuction(auctionDTO);
        return future.get();            // Waits for the async operation to complete
    }


    @DeleteMapping("/deleteAuction")
    public void deleteAuction(@RequestBody AuctionDTO auctionDTO){
        myAuctionsService.deleteAuction(auctionDTO);
    }


}
