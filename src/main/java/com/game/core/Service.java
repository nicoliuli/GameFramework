package com.game.core;

import com.game.core.func.Func0;
import com.game.core.func.Func1;
import com.game.core.func.Func2;
import com.game.core.func.Func3;
import com.game.core.util.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Service extends Thread {
    protected String serviceId;

    public Port port;

    public Map<String, Object> map = new HashMap<>();

    // port -> service
    private LinkedBlockingQueue queue;

    public Service() {
    }


    @Override
    public void run() {
        Log.info("serviceId = {} start",serviceId);
        loop();
    }


    private void loop() {
        while (true) {
            while (!queue.isEmpty()) {
                processService("");
            }
        }
    }

    private void processService(String methodKey) {
        int size = 2;
        Object obj = map.get(methodKey);
        if (size == 0) {
            Func0 func0 = (Func0) obj;
            func0.apply();
        } else if (size == 1) {
            Func1 func1 = (Func1) obj;
            func1.apply("");
        } else if (size == 2) {
            Func2 func2 = (Func2) obj;
            func2.apply("", new Object());
        } else if (size == 3) {
            Func3 func3 = (Func3) obj;
            func3.apply("", new Object(), new Date());
        }
    }

    abstract public void regMethod();

    abstract public String getServiceId();
}
