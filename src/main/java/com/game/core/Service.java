package com.game.core;

import com.game.core.call.SCall;
import com.game.core.call.ServiceCall;
import com.game.core.call.ServiceCallback;
import com.game.core.constant.ServiceConstant;
import com.game.core.func.*;
import com.game.core.util.Log;
import com.game.core.util.Param;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Service extends Thread {
    protected String serviceId;

    public Port port;

    // service方法映射
    public Map<String, Object> methodMapping;

    public Map<String, Object> callbackMethodMapping;

    // port -> service 和 service -> service callback
    public LinkedBlockingQueue<SCall> queue;


    public Service(String serviceId, Port port) {
        this.serviceId = serviceId;
        this.port = port;
        this.methodMapping = new HashMap<>();
        this.callbackMethodMapping = new HashMap<>();
        queue = new LinkedBlockingQueue<>();
        this.port.services.put(getServiceId(), this);
        regMethod();
    }

    @Override
    public void run() {
        Log.info("portId = ", port.getPortId(), ",serviceId = ", serviceId, " start!");
        loop();
    }


    private void loop() {
        while (true) {
            try {
                SCall call = queue.take();
                Integer type = call.getType();
                if (type == 1) {
                    processService((ServiceCall) call);
                } else {
                    processServiceCallBack((ServiceCallback) call);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // service队列
    private void processService(ServiceCall call) {
        String methodKey = call.getMethodKey();
        Object[] field = call.getField().getParam();
        int size = field == null ? 0 : field.length;
        Object obj = methodMapping.get(methodKey);

        if (size == 0) {
            ((Func0) obj).apply();
        } else if (size == 1) {
            ((Func1) obj).apply(field[0]);
        } else if (size == 2) {
            ((Func2) obj).apply(field[0], field[1]);
        } else if (size == 3) {
            ((Func3) obj).apply(field[0], field[1], field[2]);
        } else if (size == 4) {
            ((Func4) obj).apply(field[0], field[1], field[2], field[3]);
        }
    }

    // service 回调队列
    private void processServiceCallBack(ServiceCallback call) {
        Object result = call.getResult();
        Param context = call.getContext();
        call.getCallbackFunc().apply(result, context);
    }

    abstract public void regMethod();

    abstract public String getServiceId();

    public void addQueue(ServiceCall call) {
        queue.add(call);
    }

    public void addCallBackQueue(ServiceCallback call) {
        queue.add(call);
    }
}
