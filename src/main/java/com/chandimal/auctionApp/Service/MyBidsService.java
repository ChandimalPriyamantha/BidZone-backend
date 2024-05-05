package com.chandimal.auctionApp.Service;


import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Util.VarList;
import com.chandimal.auctionApp.dao.MyBidsRepository;
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



    //Get all bids of selected user
    public ResponseDTO getMyBids(String user_name){
        try{
            List<Object> bidList= myBidsRepository.getMyBids(user_name);
            responseDTO.setCode(VarList.RIP_SUCCESS);
            responseDTO.setContent(bidList);
            responseDTO.setMessage("Successfully get your bids");
        }catch (Exception e){
            responseDTO.setCode(VarList.RIP_ERROR);
            responseDTO.setContent(user_name);
            responseDTO.setMessage("Error with getting your bid list");
        }

        return responseDTO;
    }
}
