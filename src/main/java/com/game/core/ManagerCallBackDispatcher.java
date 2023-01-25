package com.game.core;


import com.game.core.call.ServiceCallback;
import com.game.core.func.Func2;
import com.game.core.util.Param;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * mmanager的回调分发器
 * 单实例
 */
public class ManagerCallBackDispatcher extends Thread {

    private static final ManagerCallBackDispatcher instance = new ManagerCallBackDispatcher();
    // service的回调队列,消费后直接调用manager的callback
    private LinkedBlockingQueue<ServiceCallback> callbackQueue;
    // manager回调方法映射
    private ManagerCallbackMapping callbackMapping;

    /**
     * 线程状态
     */
    public volatile boolean start = false;

    private ManagerCallBackDispatcher() {
        this.callbackQueue = new LinkedBlockingQueue<>();
        callbackMapping = ManagerCallbackMapping.instance();
    }

    public static ManagerCallBackDispatcher instance() {
        return instance;
    }


    @Override
    public void run() {
        callbackMapping.reg();
        loop();
    }

    public void loop() {
        while (true) {
            // 找到对应的 manager 的 _result
            try {
                ServiceCallback callback = callbackQueue.take();
                callbackHandler(callback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void callbackHandler(ServiceCallback callback) {
        Object result = callback.getResult();
        Param context = callback.getContext();
        callback.getCallbackFunc().apply(result,context);
    }


    public void addCallBackQueue(ServiceCallback call) {
        this.callbackQueue.add(call);
    }


}
