package com.game.service.proxy;

import com.game.core.Port;
import com.game.core.Service;
import com.game.core.constant.ServiceConstant;

public class AccountServiceProxy {
    private String serciceId = ServiceConstant.ACCOUNT_SERVICE_ID;

    public AccountServiceProxy(Port port){
        this.port = port;
    }

    // 从port里取service
    private Port port;

    public void verify(String name,Integer age){
        Service service = port.services.get(serciceId);
        //封装参数 投入service队列
        service.addQueue(name);

    }
}
