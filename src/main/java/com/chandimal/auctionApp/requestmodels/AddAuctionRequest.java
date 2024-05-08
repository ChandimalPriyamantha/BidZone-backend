package com.chandimal.auctionApp.requestmodels;


import lombok.Data;

@Data
public class AddAuctionRequest {

  private String closingTime;
  private String createdTime;
  private double startingPrice;
  private String name;
  private String description;
  private String category;
  private String img;
  private String UserName;


}
