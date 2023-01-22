package com.game.manager;

import com.game.core.Connection;
import com.game.core.HumanObject;
import com.game.core.dto.User;
import com.game.service.proxy.AccountServiceProxy;

public class AccountManager extends ManagerBase{

    public static AccountManager inst(){
        return inst(AccountManager.class);
    }

    public void verify(HumanObject humanObj, User user) {
        Integer humanId = humanObj.getHumanId();
        Connection conn = humanObj.getConnection();
        int portId = conn.getPort().getPortId();
        AccountServiceProxy prx = new AccountServiceProxy(conn.getPort());
        prx.verify(user.getName(), user.getAge());

        String result = "verify return portId = " + portId + ", humanId = " + humanId + ",param = " + user;
        humanObj.sendMsg(result);
    }

    public void _verify_result(Object result,Object ... context) {

    }


}
