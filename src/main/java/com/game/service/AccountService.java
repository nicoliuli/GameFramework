package com.game.service;

import com.game.core.Port;
import com.game.core.Service;
import com.game.core.anno.Serv;
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

    @Serv
    public void verify(String name, Integer age) {
        System.out.println("service call name = "+name+",age = "+age+",thread = " + Thread.currentThread().getId());
    }

    @Serv
    public void equip(Integer id, Integer position, String name) {

    }

    @Override
    public void regMethod() {
        Func2<String, Integer> verify = this::verify;
        methodMapping.put(ServiceConstant.ACCOUNTSERVICE_VERIFY, verify);
        Func3<Integer, Integer, String> equip = this::equip;
        methodMapping.put(ServiceConstant.ACCOUNTSERVICE_EQUIP, equip);
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }
}
