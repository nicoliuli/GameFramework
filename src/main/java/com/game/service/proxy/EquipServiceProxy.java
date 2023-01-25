package com.game.service.proxy;

import com.game.core.Port;
import com.game.core.Service;
import com.game.core.call.ServiceCall;
import com.game.core.call.ServiceCallback;
import com.game.core.constant.ServiceConstant;
import com.game.core.util.Param;

public class EquipServiceProxy extends Proxy{
    private String serciceId = ServiceConstant.EQUIP_SERVICE_ID;

    public EquipServiceProxy(Port port) {
        super(port);
    }

    // 从port里取service
    private Port port;

    public void wear(String name, Integer age, ServiceCallback context) {
        Service service = port.services.get(serciceId);
        ServiceCall call = new ServiceCall();
        call.setPortId(port.getPortId());
        call.setServiceId(serciceId);
        call.setMethodKey(ServiceConstant.EEQUIPSERVICE_WEAR);
        call.setField(new Param(name, age,context).build());
        service.addQueue(call);
    }
}
