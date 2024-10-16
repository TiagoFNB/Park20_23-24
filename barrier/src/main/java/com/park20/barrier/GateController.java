package com.park20.barrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateController {

    @Autowired
    private Environment env;
    private boolean gateOpen = false;
    private long gateOpenTime = 0;
    private final long duration = 7000; // Desired duration for auto-close


    @PostMapping("/gate/open")
    public String openGate() {
        if (!gateOpen) {
            gateOpen = true;
            gateOpenTime = System.currentTimeMillis();
            System.out.println("Gate opened.");
            scheduleCloseGate();
            return "Gate opened.";
        } else {
            return "Gate is already open.";
        }
    }

    @PostMapping("/gate/close")
    public String closeGate() {
        if (gateOpen) {
            gateOpen = false;
            System.out.println("Gate closed.");
            return "Gate closed.";
        } else {
            return "Gate is already closed.";
        }
    }

    @Scheduled(fixedDelay = 1000) // Check every second if the gate needs to be closed
    public void scheduleCloseGate() {
        if (gateOpen && System.currentTimeMillis() - gateOpenTime >= duration) {
            gateOpen = false;
            System.out.println("Gate closed automatically after " + (duration / 1000) + " seconds.");
        }
    }
}
