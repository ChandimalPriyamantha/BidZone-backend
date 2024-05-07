package com.chandimal.auctionApp.Service;

import com.chandimal.auctionApp.dao.MyAuctionsRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
@Transactional
public class MyAuctionsService {
    @Autowired
    MyAuctionsRepository myAuctionsRepository;
    @Autowired
    ModelMapper mp;

    //Get all my auctions
    @Async
    public Future<List<Object>> getMyAuctions(String user_name){
        List <Object> auctions = myAuctionsRepository.getMyAuctions(user_name);
        return new AsyncResult<>(auctions);
    }
}
