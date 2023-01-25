package com.game.core;


import com.alibaba.fastjson.JSON;
import com.game.core.call.WSCall;
import com.game.core.call.MsgCall;
import com.game.core.func.Func1;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * manager的方法分发器
 * 单实例
 */
public class MsgHandlerDispatcher extends Thread {

    private static final MsgHandlerDispatcher instance = new MsgHandlerDispatcher();
    private LinkedBlockingQueue<MsgCall> queue;

    private MsgHandlerMapping handlerMapping;

    /**
     * 线程状态
     */
    public volatile boolean start = false;

    private MsgHandlerDispatcher() {
        this.queue = new LinkedBlockingQueue<>();
        handlerMapping =  MsgHandlerMapping.instance();
    }

    public static MsgHandlerDispatcher instance(){
        return instance;
    }


    @Override
    public void run() {
        handlerMapping.regMsgHandler();
        loop();
    }

    public void loop() {
        while (true) {
            // 找到对应的function
            try{
                MsgCall msgCall = queue.take();
                callMsgHandler(msgCall);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void callMsgHandler(MsgCall msgCall) {
        WSCall WSCall = msgCall.getWSCall();
        HumanObject humanObj = msgCall.getHumanObj();
        String msgHandlerId = WSCall.getMsgHandlerId();
        MsgParamWrapper msgParamWrapper = handlerMapping.getMsgParamWrapper(msgHandlerId);

        Func1<MsgParam> func1 = msgParamWrapper.getFunc1();
        Class clazz = msgParamWrapper.getClazz();

        MsgParam msgParam = new MsgParam();
        msgParam.setParam(JSON.parseObject(WSCall.getJsonParam(), clazz));
        msgParam.setHumanObject(humanObj);
        func1.apply(msgParam);
    }

    public void addQueue(MsgCall call) {
        this.queue.add(call);
    }


}
