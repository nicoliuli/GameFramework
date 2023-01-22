package com.game.core;

import com.game.core.call.SCall;
import com.game.core.call.ServiceCall;
import com.game.core.call.ServiceCallback;
import com.game.core.constant.ServiceConstant;
import com.game.core.func.Func0;
import com.game.core.func.Func1;
import com.game.core.func.Func2;
import com.game.core.func.Func3;
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

    // port -> service
    public LinkedBlockingQueue<SCall> queue;




    public Service(String serviceId,Port port){
        this.serviceId = serviceId;
        this.port = port;
        this.methodMapping  = new HashMap<>();
        this.callbackMethodMapping = new HashMap<>();
        queue = new LinkedBlockingQueue<>();
        this.port.services.put(getServiceId(),this);
        regMethod();
    }

    @Override
    public void run() {
        Log.info("portId = ", port.getPortId(), ",serviceId = ", serviceId, " start!");
        loop();
    }


    private void loop() {
        while (true) {
            try{
                SCall call = queue.take();
                Integer type = call.getType();
                if(type == 1){
                    processService((ServiceCall) call);
                }else {
                    processServiceCallBack((ServiceCallback) call);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // service队列
    private void processService(ServiceCall call) {
        String methodKey = call.getMethodKey();
        Object[] field = call.getField();
        int size = field == null ? 0 : field.length;
        Object obj = methodMapping.get(methodKey);

        if (size == 0) {
            Func0 func0 = (Func0) obj;
            func0.apply();
        } else if (size == 1) {
            Func1 func1 = (Func1) obj;
            func1.apply(field[0]);
        } else if (size == 2) {
            Func2 func2 = (Func2) obj;
            func2.apply(field[0], field[1]);
        } else if (size == 3) {
            Func3 func3 = (Func3) obj;
            func3.apply(field[0], field[1], field[2]);
        }
    }

    // service 回调队列
    private void processServiceCallBack(ServiceCallback call) {
        String methodKey = call.getMethodKey();

        Func2 func2 = (Func2)callbackMethodMapping.get(methodKey);
        Object result = call.getResult();
        Param context = call.getContext();
        func2.apply(result,context);

    }

    abstract public void regMethod();

    abstract public String getServiceId();

    public void addQueue(ServiceCall call) {
        queue.add(call);
    }

    public void addCallBackQueue(ServiceCallback call) {
        queue.add(call);
    }

    public Object [] parseField(Object ... params){
        return params;
    }
}
