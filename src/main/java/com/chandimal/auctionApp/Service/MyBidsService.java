package com.chandimal.auctionApp.Service;


import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Util.VarList;
import com.chandimal.auctionApp.dao.MyBidsRepository;
import com.chandimal.auctionApp.entity.Bid;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Transactional
public class MyBidsService {

    @Autowired
    MyBidsRepository myBidsRepository;
    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    ModelMapper mp;



    //Get all bids of selected user
    @Async
    public Future<List<Object>> getMyBids(String user_name){
        List<Object> bid=  myBidsRepository.getMyBids(user_name);
        return new AsyncResult<>(bid);

    }


    //update bid
    @Async
    public Future<String> updateBid(BidDTO bidDTO){
        if(myBidsRepository.existsById(bidDTO.getId())){

            myBidsRepository.save(mp.map(bidDTO, Bid.class));
            return new AsyncResult<>("Updated successfully");
        }else{
            return new AsyncResult<>("Matching data is not found");
        }

    }

    //Get all bids related to an auction
    @Async
    public Future<List<BidDTO>> getBidOnItem(Integer auction_id){
        List<Bid> bidList= myBidsRepository.getBidOnItem(auction_id);
        return new AsyncResult<>(mp.map(bidList,new TypeToken<ArrayList<BidDTO>>(){}.getType()));
    }


    //delete bid
    @Async
    public void deleteBid (BidDTO bidDTO){
        if(myBidsRepository.existsById(bidDTO.getId())){
            myBidsRepository.delete(mp.map(bidDTO,Bid.class));
        }

    }


    @Async
    //Get the highest Bid related to an auction
    public Future<List<BidDTO>> highestBid(Integer auction_id){
        List<Bid> highestBid= myBidsRepository.highestBid(auction_id);
        return new AsyncResult<>(mp.map(highestBid,new TypeToken<ArrayList<BidDTO>>(){}.getType())) ;
    }


}
