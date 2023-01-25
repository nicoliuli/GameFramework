package com.game.service.proxy;

import com.game.core.Port;
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
        invoke(serciceId,ServiceConstant.EEQUIPSERVICE_WEAR,new Param(name, age,context));
    }
}
