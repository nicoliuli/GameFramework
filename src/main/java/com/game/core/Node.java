package com.game.core;

import com.alibaba.fastjson.JSON;
import com.game.core.config.ServerConfig;
import com.game.core.util.Log;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Node extends Thread {
    private static Node instance;
    private int nodeId;
    private ConcurrentMap<Integer, Port> ports;

    // ws -> node
    private LinkedBlockingQueue<Call> queue;


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
            ports.put(i, port);
            port.start();
        }
        loop();
    }

    private void loop() {
        // 监听队列
        while (true) {
            while (!queue.isEmpty()) {
                // 投入port队列
                Call call = queue.poll();
                nodeMessageHandler(call);
            }
        }
    }

    public void nodeMessageHandler(Call call) {
        Log.info("node call = ", call);
        // 解码，分派port

        if(ServerConfig.NODE_ID != call.getFromNodeId()){
            // 发到rpc队列
            return;
        }
        // 投入port queue
        Integer toPortId = call.getToPortId();
        Port port = Node.instance().ports.get(toPortId);
        call.setQueueType(2);
        port.addQueue(call);
    }


    public static Node instance() {
        return instance;
    }

    public Port getPort(Integer portId) {
        return ports.get(portId);
    }

    public void addQueue(Call call) {
        this.queue.add(call);
    }

}
