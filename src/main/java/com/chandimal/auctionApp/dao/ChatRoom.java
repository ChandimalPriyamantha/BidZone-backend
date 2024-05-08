package com.chandimal.auctionApp.dao;

import com.chandimal.auctionApp.DTO.Message;

public class ChatRoom implements Observer{
    @Override
    public void update(Message message) {
        System.out.println("New message in chatroom: " + message.toString());
    }
}
