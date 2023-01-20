package com.game.core;

import com.game.core.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Port extends Thread {
    private int portId;
    private Node node;
    private List<Service> services;
    private MsgHandlerDispatcher msgDispatcher;

    // node -> port
    private LinkedBlockingQueue<Call> queue;

    private ConcurrentMap<Integer,Connection> conns;

    public Port(int portId) {
        this.portId = portId;
        this.queue = new LinkedBlockingQueue();
        this.services = new ArrayList<>();
        this.conns = new ConcurrentHashMap();
        this.msgDispatcher = new MsgHandlerDispatcher();
    }

    @Override
    public void run() {
        Log.info("port start,portId = ", portId);
        msgDispatcher.start();
        loop();
    }

    public void loop() {
        while (true) {
            while (!queue.isEmpty()) {
                // 找到对应的MsgHandler
                Call call = queue.poll();
                portMessageHandler(call);
            }
        }
    }

    public void portMessageHandler(Call call) {
        Integer humanId = call.getHumanId();
        Connection connection = conns.get(humanId);
        // 封装humanObj
        HumanObject humanObj = new HumanObject();
        humanObj.setHumanId(humanId);
        humanObj.setConnection(connection);

        MsgCall msgCall = new MsgCall();
        msgCall.setCall(call);
        msgCall.setHumanObj(humanObj);
        // 投递function队列
        msgDispatcher.addQueue(msgCall);
    }

    public void addQueue(Call call) {
        this.queue.add(call);
    }

    public void addConn(Connection conn) {
        this.conns.putIfAbsent(conn.getHumanId(), conn);
    }

    public void removeConn(Integer humanId) {
        this.conns.remove(humanId);
    }

    public int getPortId() {
        return portId;
    }
}
