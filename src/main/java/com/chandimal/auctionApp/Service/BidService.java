package com.chandimal.auctionApp.Service;

import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.DTO.ResponseDTO;
import com.chandimal.auctionApp.Util.VarList;
import com.chandimal.auctionApp.dao.BidRepository;
import com.chandimal.auctionApp.entity.Bid;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class BidService  {

    @Autowired
    private BidRepository bidRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDTO responseDTO;

    public CompletableFuture<ResponseDTO> placeBid(BidDTO bidDTO) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                synchronized (this) {
                    bidRepository.save(modelMapper.map(bidDTO, Bid.class));
                }
                responseDTO.setCode(VarList.RIP_SUCCESS);
                responseDTO.setContent(bidDTO);
                responseDTO.setMessage("Successfully added Bid");
            } catch (Exception e) {
                responseDTO.setCode(VarList.RIP_ERROR);
                responseDTO.setContent(bidDTO);
                responseDTO.setMessage("Error adding Bid");
            }
            return responseDTO;
        });
    }




    public ResponseDTO getBidsOnITem(int auction_id)
    {
        try
        {
            List<Bid> bidList=bidRepository.getBidsOnitem(auction_id);

            responseDTO.setCode(VarList.RIP_SUCCESS);
            responseDTO.setContent(modelMapper.map(bidList,new TypeToken<ArrayList<Bid>>(){}.getType()));
            responseDTO.setMessage("Successfully added Bid");

        }catch (Exception e)
        {
            responseDTO.setCode(VarList.RIP_ERROR);
            responseDTO.setContent(auction_id);
            responseDTO.setMessage("Error adding Bid");
        }
        return responseDTO;
    }

}