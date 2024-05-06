package com.chandimal.auctionApp.Service;


import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Util.VarList;
import com.chandimal.auctionApp.dao.MyBidsRepository;
import com.chandimal.auctionApp.entity.Bid;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MyBidsService {

    @Autowired
    MyBidsRepository myBidsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    ModelMapper mp;



    //Get all bids of selected user
    public List<Object> getMyBids(String user_name){
         return myBidsRepository.getMyBids(user_name);

    }


    //update bid
    public String updateBid(BidDTO bidDTO){
        if(myBidsRepository.existsById(bidDTO.getId())){

            myBidsRepository.save(mp.map(bidDTO, Bid.class));
            return ("Updated successfully");
        }else{
            return ("Matching data is not found");
        }

    }


    //delete bid
    public void deleteBid (BidDTO bidDTO){
        if(myBidsRepository.existsById(bidDTO.getId())){
            myBidsRepository.delete(mp.map(bidDTO,Bid.class));
        }

    }
}
