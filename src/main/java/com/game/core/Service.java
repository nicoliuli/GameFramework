package com.game.core;

import com.game.core.call.ServiceCall;
import com.game.core.func.Func0;
import com.game.core.func.Func1;
import com.game.core.func.Func2;
import com.game.core.func.Func3;
import com.game.core.util.Log;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Service extends Thread {
    protected String serviceId;

    public Port port;

    // service方法映射
    public Map<String, Object> methodMapping;

    // port -> service
    public LinkedBlockingQueue<ServiceCall> queue;


    @Override
    public void run() {
        Log.info("portId = ", port.getPortId(), ",serviceId = ", serviceId, " start!");
        loop();
    }


    private void loop() {
        while (true) {
            try{
                ServiceCall call = queue.take();
                processService(call);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

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

    abstract public void regMethod();

    abstract public String getServiceId();

    public void addQueue(ServiceCall call) {
        queue.add(call);
    }

    public Object [] parseField(Object ... params){
        return params;
    }
}
