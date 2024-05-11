package com.chandimal.auctionApp.Service;


import com.chandimal.auctionApp.requestmodels.NewListenerUpdates;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

@Service
public class NewListenerUpdatesService {

   private final ObjectMapper objectMapper;

   @Autowired
    public NewListenerUpdatesService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // to serialize object
    public String serialize(NewListenerUpdates newListenerUpdates) throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(newListenerUpdates);
        oos.flush();
        byte[] bytes = bos.toByteArray();
        oos.close();
        return Base64.getEncoder().encodeToString(bytes);
    }

    // to deserialize object
    public  NewListenerUpdates deserialize(String json) throws Exception {
        return objectMapper.readValue(json,NewListenerUpdates.class);
    }
}
