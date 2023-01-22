package com.game.core;

import com.game.core.call.WSCall;
import com.game.core.call.MsgCall;
import com.game.core.call.ServiceCallback;
import com.game.core.util.Log;
import com.game.service.AccountService;
import com.game.service.EquipService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Port extends Thread {
    private int portId;
    private Node node;
    public Map<String, Service> services;
    private static MsgHandlerDispatcher msgDispatcher;
    private static ManagerCallBackDispatcher managerCallBackDispatcher;

    // node -> port
    private LinkedBlockingQueue<WSCall> queue;


    private ConcurrentMap<Integer, Connection> conns;

    public Port(int portId) {
        this.portId = portId;
        this.queue = new LinkedBlockingQueue();

        this.services = new HashMap();
        this.conns = new ConcurrentHashMap();
        this.msgDispatcher = MsgHandlerDispatcher.instance();
        this.managerCallBackDispatcher = ManagerCallBackDispatcher.instance();
    }

    private void regAndStartService() {
        // 注册service方法
        AccountService accountService = new AccountService(this);
        EquipService equipService = new EquipService(this);

        // 注册service回调

        for (Map.Entry<String, Service> entry : services.entrySet()) {
            entry.getValue().start();
        }
    }

    @Override
    public void run() {
        Log.info("port start,portId = ", portId);
        // 启动service
        regAndStartService();

        // 启动MsgHandlerDispatcher
        if (!msgDispatcher.start) {
            msgDispatcher.start = true;
            msgDispatcher.start();
        }
        if(!managerCallBackDispatcher.start){
            managerCallBackDispatcher.start = true;
            managerCallBackDispatcher.start();
        }

        loop();
    }

    public void loop() {
        while (true) {
            try {
                // 找到对应的MsgHandler
                WSCall WSCall = queue.take();
                portMessageHandler(WSCall);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void portMessageHandler(WSCall WSCall) {
        Integer humanId = WSCall.getHumanId();
        Connection connection = conns.get(humanId);
        // 封装humanObj
        HumanObject humanObj = new HumanObject();
        humanObj.setHumanId(humanId);
        humanObj.setConnection(connection);

        MsgCall msgCall = new MsgCall();
        msgCall.setWSCall(WSCall);
        msgCall.setHumanObj(humanObj);
        // 投递function队列
        msgDispatcher.addQueue(msgCall);
    }

    public void addQueue(WSCall WSCall) {
        this.queue.add(WSCall);
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

    public void ret(ServiceCallback ret) {
        Integer callbackType = ret.getCallbackType();
        if (callbackType == 1) {
            // 投入manager回调队列
            managerCallBackDispatcher.addCallBackQueue(ret);
        } else {
            // 投入service回调队列
            String serviceId = ret.getServiceId();
            Service service = services.get(serviceId);
            service.addCallBackQueue(ret);
        }
    }
}
