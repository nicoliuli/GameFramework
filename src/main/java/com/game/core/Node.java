package com.game.core;

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
    private LinkedBlockingQueue<WSCall> queue;


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
                WSCall WSCall = queue.take();
                nodeMessageHandler(WSCall);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void nodeMessageHandler(WSCall WSCall) {
        // 解码，分派port
        if(ServerConfig.NODE_ID != WSCall.getFromNodeId()){
            // 发到rpc队列
            return;
        }
        // 投入port queue
        Integer toPortId = WSCall.getToPortId();
        Port port = Node.instance().ports.get(toPortId);
        port.addQueue(WSCall);
    }


    public static Node instance() {
        return instance;
    }

    public Port getPort(Integer portId) {
        return ports.get(portId);
    }

    public void addQueue(WSCall WSCall) {
        this.queue.add(WSCall);
    }

}
