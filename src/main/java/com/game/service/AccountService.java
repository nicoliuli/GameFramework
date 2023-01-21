package com.game.service;

import com.game.core.Port;
import com.game.core.Service;
import com.game.core.constant.ServiceConstant;
import com.game.core.func.Func2;
import com.game.core.func.Func3;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class AccountService extends Service {


    public AccountService(Port port){
        this.serviceId = ServiceConstant.ACCOUNT_SERVICE_ID;
        this.port = port;
        methodMapping  = new HashMap<>();
        queue = new LinkedBlockingQueue();
        port.services.put(getServiceId(),this);
        regMethod();
    }

    public void verify(String name, Integer age) {

    }

    public void equip(Integer id, Integer position, String name) {

    }

    @Override
    public void regMethod() {
     //   AccountService accountService = (AccountService) service;
        Func2<String, Integer> verify = this::verify;
        methodMapping.put("AccountService.verify", verify);
        Func3<Integer, Integer, String> equip = this::equip;
        methodMapping.put("AccountService.equip", equip);
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }
}
