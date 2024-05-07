package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.DTO.Message;

public interface Observer {
    void update(Message message);
}
