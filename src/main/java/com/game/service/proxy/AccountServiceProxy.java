package com.game.service.proxy;

import com.game.core.Port;
import com.game.core.Service;
import com.game.core.call.ServiceCall;
import com.game.core.call.ServiceCallback;
import com.game.core.constant.ServiceConstant;
import com.game.core.util.Param;

public class AccountServiceProxy {
    private String serciceId = ServiceConstant.ACCOUNT_SERVICE_ID;

    public AccountServiceProxy(Port port) {
        this.port = port;
    }

    // 从port里取service
    private Port port;

    public void verify(String name, Integer age, ServiceCallback context) {
        Service service = port.services.get(serciceId);
        ServiceCall call = new ServiceCall();
        call.setPortId(port.getPortId());
        call.setServiceId(serciceId);
        call.setMethodKey(ServiceConstant.ACCOUNTSERVICE_VERIFY);
        call.setField(new Param(name, age,context).build());
        service.addQueue(call);
    }
}
