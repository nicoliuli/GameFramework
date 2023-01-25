package com.game.manager;

import com.game.core.Connection;
import com.game.core.HumanObject;
import com.game.core.anno.CallBack;
import com.game.core.call.ServiceCallback;
import com.game.core.constant.ManagerConstant;
import com.game.core.dto.User;
import com.game.core.func.Func2;
import com.game.core.util.Param;
import com.game.service.proxy.AccountServiceProxy;

public class AccountManager extends ManagerBase {

    public static AccountManager inst() {
        return inst(AccountManager.class);
    }

    public void verify(HumanObject humanObj, User user) {
        Integer humanId = humanObj.getHumanId();
        Connection conn = humanObj.getConnection();
        int portId = conn.getPort().getPortId();
        AccountServiceProxy prx = new AccountServiceProxy(conn.getPort());
        ServiceCallback context = new ServiceCallback(portId, 1, (Func2<Object, Param>) this::_verify_result, new Param(humanObj).build());
        prx.verify(user.getName(), user.getAge(), context);

        String result = "verify return portId = " + portId + ", humanId = " + humanId + ",param = " + user;
        humanObj.sendMsg(result);
    }

    @CallBack
    public void _verify_result(Object result, Param context) {
        System.out.println("result = " + result + "thread = " + Thread.currentThread().getId());
        Object[] ctx = context.getParam();
        HumanObject humanObj = (HumanObject) ctx[0];
        humanObj.sendMsg("收到回调 humanId = " + humanObj.getHumanId() + ", result = " + (String) result);
    }


}
