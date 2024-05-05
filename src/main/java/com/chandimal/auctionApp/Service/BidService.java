package com.chandimal.auctionApp.Service;

import com.chandimal.auctionApp.DTO.BidDTO;
import com.chandimal.auctionApp.DTO.ResponseDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.CompletableFuture;

public interface BidService extends Remote {
    CompletableFuture<ResponseDTO> placeBid(BidDTO bidDTO) throws RemoteException;
}
