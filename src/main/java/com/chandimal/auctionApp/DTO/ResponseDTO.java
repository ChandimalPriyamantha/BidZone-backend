package com.chandimal.auctionApp.DTO;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ResponseDTO extends CompletableFuture<ResponseDTO> {
    private String code;

    private Object content;

    private String message;
}
