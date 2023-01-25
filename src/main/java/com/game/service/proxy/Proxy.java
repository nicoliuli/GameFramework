package com.game.service.proxy;

import com.game.core.Port;
import com.game.core.Service;
import com.game.core.call.ServiceCall;
import com.game.core.util.Param;

abstract public class Proxy {
    // 从port里取service
    final Port port;

    public Proxy(Port port) {
        this.port = port;
    }

    /**
     * 投入service queue
     *
     * @param serviceId
     * @param methodKey
     * @param param
     */
    void invoke(String serviceId, String methodKey, Param param) {
        Service service = port.services.get(serviceId);
        ServiceCall call = new ServiceCall();
        call.setPortId(port.getPortId());
        call.setServiceId(serviceId);
        call.setMethodKey(methodKey);
        call.setField(param);
        service.addQueue(call);
    }
}
