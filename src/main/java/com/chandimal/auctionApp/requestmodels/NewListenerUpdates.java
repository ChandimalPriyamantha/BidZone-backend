package com.chandimal.auctionApp.requestmodels;


import lombok.Data;

import java.io.Serializable;

@Data
public class NewListenerUpdates implements Serializable {

    private String productName;
    private String description;
    private double startingPrice;
    private String closingTime;
    private String UserName;
}
