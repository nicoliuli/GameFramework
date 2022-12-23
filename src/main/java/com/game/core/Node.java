package com.game.core;

import com.game.core.config.ServerConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Node extends Thread {
    private static Node instance;
    private int nodeId;
    private ConcurrentMap<Integer,Port> ports;

    // ws -> node
    private LinkedBlockingQueue queue;


    public Node(int nodeId) {
        this.nodeId = nodeId;
        this.ports = new ConcurrentHashMap<>();
        this.queue = new LinkedBlockingQueue();
        instance = this;
    }

    @Override
    public void run() {
        // 启动 port
        for (int i = 0; i < ServerConfig.PORT_COUNT; i++) {
            Port port = new Port(i);
            ports.put(i,port);
            port.start();
        }
        loop();
    }

    private void loop() {
        // 监听队列
        while (true) {
            while (!queue.isEmpty()) {
                // 投入port队列
            }
        }
    }


    public static Node getNode(){
        return instance;
    }

    public Port getPort(Integer portId) {
        return ports.get(portId);
    }

}
