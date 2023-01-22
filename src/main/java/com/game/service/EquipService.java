package com.game.service;

import com.game.core.Port;
import com.game.core.Service;
import com.game.core.call.ServiceCallback;
import com.game.core.constant.ServiceConstant;
import com.game.core.func.Func2;
import com.game.core.func.Func3;
import com.game.core.util.Param;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class EquipService extends Service {


    public EquipService(Port port){
        super(ServiceConstant.EQUIP_SERVICE_ID,port);
    }

    public void wear(String name, Integer age,ServiceCallback context) {
        System.out.println(name + ":" + age);
        if(context != null){
            context.setResult("equip result");
            port.ret(context);
        }

    }



    @Override
    public void regMethod() {
        Func3<String, Integer,ServiceCallback> wear = this::wear;
        methodMapping.put(ServiceConstant.EEQUIPSERVICE_WEAR, wear);
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }
}
