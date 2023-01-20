package com.game.service;

import com.game.core.Service;
import com.game.core.func.Func2;
import com.game.core.func.Func3;

public class AccountService extends Service {

    private AccountService accountService = new AccountService();

    public AccountService(){
        this.serviceId = "AccountService";
        port.services.put(this.getServiceId(),accountService);
    }

    public void verify(String name, Integer age) {

    }

    public void equip(Integer id, Integer position, String name) {

    }

    @Override
    public void regMethod() {
        Func2<String, Integer> verify = accountService::verify;
        map.put("AccountService.verify", verify);
        Func3<Integer, Integer, String> equip = accountService::equip;
        map.put("AccountService.equip", equip);
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }
}
