package com.game.service;

import com.game.core.Port;
import com.game.core.Service;
import com.game.core.anno.CallBack;
import com.game.core.anno.Serv;
import com.game.core.call.ServiceCallback;
import com.game.core.constant.ServiceConstant;
import com.game.core.func.Func2;
import com.game.core.func.Func3;
import com.game.core.util.Param;
import com.game.service.proxy.EquipServiceProxy;

public class AccountService extends Service {


    public AccountService(Port port) {
        super(ServiceConstant.ACCOUNT_SERVICE_ID, port);
    }

    @Serv
    public void verify(String name, Integer age, ServiceCallback context) {
        System.out.println("service call name = " + name + ",age = " + age + ",thread = " + Thread.currentThread().getId());

        EquipServiceProxy prx = new EquipServiceProxy(port);
        ServiceCallback ctx =
                new ServiceCallback(port.getPortId(), serviceId, 2,
                        (Func2<Object, Param>) this::_result_verify, new Param(context).build());
        prx.wear(name, age, ctx);

        /*if (context != null) {
            // 投入回调队列
            context.setResult("result = 1");
            port.ret(context);
        }*/
    }

    @Serv
    public void equip(Integer id, Integer position, String name) {

    }

    @CallBack
    public void _result_verify(Object result, Param context) {
        System.out.println("_result_verify = " + result.toString() + "thread = " + Thread.currentThread().getId());
        Object[] param = context.getParam();
        ServiceCallback callback = (ServiceCallback) param[0];
        /*Object[] param1 = callback.getContext().getParam();
        HumanObject humanObj = (HumanObject) param1[0];
        humanObj.sendMsg("最终返回");*/
        callback.setResult("finally");
        port.ret(callback);
    }

    @Override
    public void regMethod() {
        // 注册service方法
        Func3<String, Integer, ServiceCallback> verify = this::verify;
        methodMapping.put(ServiceConstant.ACCOUNTSERVICE_VERIFY, verify);
        Func3<Integer, Integer, String> equip = this::equip;
        methodMapping.put(ServiceConstant.ACCOUNTSERVICE_EQUIP, equip);


        // 注册回调方法
        Func2<Object, Param> result_verify = this::_result_verify;
        callbackMethodMapping.put(ServiceConstant.ACCOUNTSERVICE_RESULT_VERIFY, result_verify);
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }
}
