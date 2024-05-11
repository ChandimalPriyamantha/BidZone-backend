package com.chandimal.auctionApp.Controller;


import com.chandimal.auctionApp.requestmodels.NewListenerUpdates;
import com.chandimal.auctionApp.Service.NewListenerUpdatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/auction")
public class newListenerUpdateController {

    private final NewListenerUpdatesService newListenerUpdatesService;

    @Autowired
    public newListenerUpdateController(NewListenerUpdatesService newListenerUpdatesService) {
        this.newListenerUpdatesService = newListenerUpdatesService;
    }

    // to control deserialization
    @GetMapping("/deserialize")
    public NewListenerUpdates deserialize(@RequestParam("data") String data) throws Exception{


        // Decode the base64 string
        byte[] bytes = Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        NewListenerUpdates obj = (NewListenerUpdates) ois.readObject();
        ois.close();
        return obj;

    }

}
