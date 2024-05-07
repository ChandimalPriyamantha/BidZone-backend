package com.chandimal.auctionApp.Controller;

import com.chandimal.auctionApp.DTO.Message;
import com.chandimal.auctionApp.dao.ChatRoom;
import com.chandimal.auctionApp.dao.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    public SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController() {
        addObserver(new ChatRoom());
    }

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message) {
        notifyObservers(message);
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }


}
