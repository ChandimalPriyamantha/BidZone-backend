package com.chandimal.auctionApp.Controller;

import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Service.MyBidsService;
import com.chandimal.auctionApp.Util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/MyBids")
public class MyBidsController {

    @Autowired
    MyBidsService myBidsService;

    //Get all bids of selected user
    @GetMapping("/getMyBids{user_name}")
    public ResponseEntity getMyBids(@PathVariable String user_name){
        ResponseDTO responseDTO=myBidsService.getMyBids(user_name);
        if(responseDTO.getCode().equals(VarList.RIP_SUCCESS)){
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }
}
