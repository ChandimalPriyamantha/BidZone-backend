package com.chandimal.auctionApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BidDTO
{
    private int id;

    private double amount;

    private String comment;

    private String placed_at;

    private int auction_id;

    private String user_name;

    private int highest_id;
}
