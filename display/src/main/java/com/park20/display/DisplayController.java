package com.park20.display;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisplayController {

    @PostMapping("/display")
    public String displayText(@RequestBody DisplayTextDTO text) {
        // Simulating displaying the received text
        displayInParkingLot(text.getText());
        return "Text received and displayed in the parking lot.";
    }

    private void displayInParkingLot(String text) {
        System.out.println(text);
    }
}

