package com.chandimal.auctionApp.Controller;

import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Service.BidService;
import com.chandimal.auctionApp.Util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private BidService bidService;

    @GetMapping("getNotification/{user_name}/{auction_id}")
    public ResponseEntity getNewHighestBid(@PathVariable String user_name,@PathVariable int auction_id)
    {
        ResponseDTO responseDTO=bidService.getNotification(user_name,auction_id);
        if (responseDTO.getCode().equals(VarList.RIP_SUCCESS))
        {
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(responseDTO,HttpStatus.NOT_FOUND);
        }
    }
}
