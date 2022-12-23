package com.game.core;

import com.game.core.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Port extends Thread {
    private int portId;
    private Node node;
    private List<Service> services;

    // node -> port
    private LinkedBlockingQueue queue;

    public Port(int portId) {
        this.portId = portId;
        this.queue = new LinkedBlockingQueue();
        this.services = new ArrayList<>();
    }

    @Override
    public void run() {
        Log.info("port start,portId = ", portId);
        loop();
    }

    public void loop() {
        while (true) {
            while (!queue.isEmpty()) {
                // 投入service队列
            }
        }
    }
}
