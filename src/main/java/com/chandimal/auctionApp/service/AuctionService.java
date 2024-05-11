package com.chandimal.auctionApp.Service;


import com.chandimal.auctionApp.dao.AuctionRepository;
import com.chandimal.auctionApp.entity.Auction;
import com.chandimal.auctionApp.requestmodels.AddAuctionRequest;
import com.chandimal.auctionApp.requestmodels.NewListenerUpdates;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuctionService {

    private AuctionRepository auctionRepository;
    private NewListenerUpdatesService newListenerUpdatesService; // to work with object serialization


    @Autowired
    public AuctionService(AuctionRepository auctionRepository, NewListenerUpdatesService newListenerUpdatesService) {
        this.auctionRepository = auctionRepository;
        this.newListenerUpdatesService = newListenerUpdatesService;
    }

    // To save new auction details.
    public void addAuction(AddAuctionRequest addAuctionRequest) throws Exception {

        Auction auction = new Auction();
        auction.setClosingTime(addAuctionRequest.getClosingTime());
        auction.setCreatedTime(addAuctionRequest.getCreatedTime());
        auction.setStartingPrice(addAuctionRequest.getStartingPrice());
        auction.setName(addAuctionRequest.getName());
        auction.setDescription(addAuctionRequest.getDescription());
        auction.setCategory(addAuctionRequest.getCategory());
        auction.setImg(addAuctionRequest.getImg());
        auction.setUserName(addAuctionRequest.getUserName());
        auctionRepository.save(auction);

        // create a serializable object
        NewListenerUpdates newListenerUpdates = new NewListenerUpdates();
        newListenerUpdates.setClosingTime(addAuctionRequest.getClosingTime());
        newListenerUpdates.setDescription(addAuctionRequest.getDescription());
        newListenerUpdates.setUserName(addAuctionRequest.getUserName());
        newListenerUpdates.setProductName(addAuctionRequest.getName());
        newListenerUpdates.setStartingPrice(addAuctionRequest.getStartingPrice());
        // get serializable code.
        String x = newListenerUpdatesService.serialize(newListenerUpdates);
        System.out.println(x);


    }
}
