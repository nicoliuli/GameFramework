package com.game.core;

import java.util.concurrent.LinkedBlockingQueue;

public class Service extends Thread {
    private int serviceId;

    private Port port;

    // port -> service
    private LinkedBlockingQueue queue;

    public Service() {
    }

    public Service(int serviceId) {
        this.serviceId = serviceId;
        this.queue = new LinkedBlockingQueue();
    }

    @Override
    public void run() {

        loop();
    }


    private void loop() {
        while (true) {
            while (!queue.isEmpty()) {

            }
        }
    }
}
