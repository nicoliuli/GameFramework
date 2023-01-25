package com.game.service.proxy;

import com.game.core.Port;
import com.game.core.call.ServiceCallback;
import com.game.core.constant.ServiceConstant;
import com.game.core.util.Param;

public class AccountServiceProxy extends Proxy{
    private String serciceId = ServiceConstant.ACCOUNT_SERVICE_ID;

    public AccountServiceProxy(Port port) {
       super(port);
    }



    public void verify(String name, Integer age, ServiceCallback context) {
        invoke(serciceId,ServiceConstant.ACCOUNTSERVICE_VERIFY,new Param(name, age,context));
    }
}
