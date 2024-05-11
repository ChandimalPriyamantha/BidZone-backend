package com.chandimal.auctionApp.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuctionDTO {
    private Long id;

    private String closingTime;

    private String createdTime;

    private double startingPrice;

    private String name;

    private String description;

    private String category;

    private String img;

    private String userName;

    private int highestBid;
}
