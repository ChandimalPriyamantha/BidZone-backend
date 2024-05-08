package com.chandimal.auctionApp.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "auction")
@Data
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "closing_time")
    private String closingTime;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "starting_price")
    private double startingPrice;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "img")
    private String img;

    @Column(name = "user_name")
    private String userName;


}
