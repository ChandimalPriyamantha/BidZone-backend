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
    private BidService bidService;

    @PostMapping("placeBid")
    public ResponseEntity placeBid(@RequestBody BidDTO bidDTO) throws ExecutionException, InterruptedException {
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

    @GetMapping("/getBidOnItem/{auction_id}")
    public ResponseEntity getBidOnItem(@PathVariable int auction_id)
    {
        ResponseDTO responseDTO=bidService.getBidsOnITem(auction_id);
        if(responseDTO.getCode().equals(VarList.RIP_SUCCESS))
        {
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }
}
