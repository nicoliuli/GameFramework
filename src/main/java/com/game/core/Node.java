package com.game.core;

import com.game.core.call.MsgCall;
import com.game.core.call.WSCall;
import com.game.core.config.ServerConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Node extends Thread {
    private static Node instance;
    private int nodeId;
    private ConcurrentMap<Integer, Port> ports;

    // ws -> node
    private LinkedBlockingQueue<MsgCall> queue;


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
            try{
                // 投入port队列
                MsgCall msgCall = queue.take();
                nodeMessageHandler(msgCall);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void nodeMessageHandler(MsgCall msgCall) {
        Port port = msgCall.getHumanObj().getConnection().getPort();
        port.addQueue(msgCall);
    }


    public static Node instance() {
        return instance;
    }

    public Port getPort(Integer portId) {
        return ports.get(portId);
    }

    public void addQueue(MsgCall msgCall) {
        this.queue.add(msgCall);
    }

}
