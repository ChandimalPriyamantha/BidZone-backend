package com.chandimal.auctionApp.controller;


import com.chandimal.auctionApp.requestmodels.AddAuctionRequest;
import com.chandimal.auctionApp.requestmodels.NewListenerUpdates;
import com.chandimal.auctionApp.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    private AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    // API endpoint to save new auction details.
    @PostMapping("/addAuction")
    public void creatAuction(@RequestBody AddAuctionRequest addAuctionRequest) throws Exception {

       auctionService.addAuction(addAuctionRequest); // To pass API data into the addAuction method that is created in AuctionService.

    }




}
