package com.game.service;

import com.game.core.Port;
import com.game.core.Service;
import com.game.core.func.Func2;
import com.game.core.func.Func3;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class EquipService extends Service {


    public EquipService(Port port){
        this.serviceId = "EquipService";
        this.port = port;
        methodMapping  = new HashMap<>();
        queue = new LinkedBlockingQueue();
        port.services.put(getServiceId(),this);
        regMethod();
    }

    public void wear(String name, Integer age) {

    }



    @Override
    public void regMethod() {
    //    EquipService equipService = (EquipService) service;
        Func2<String, Integer> wear = this::wear;
        methodMapping.put("EquipService.wear", wear);
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }
}
